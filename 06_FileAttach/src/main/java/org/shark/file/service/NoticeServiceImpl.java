package org.shark.file.service;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

import org.shark.file.model.dto.AttachDTO;
import org.shark.file.model.dto.NoticeDTO;
import org.shark.file.repository.NoticeDAO;
import org.shark.file.util.FileUtil;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class NoticeServiceImpl implements NoticeService {

  private final NoticeDAO noticeDAO;
  private final FileUtil fileUtil;
  
  @Override
  public List<NoticeDTO> findNotices() {
    return noticeDAO.getNotices();
  }

  @Override
  public Map<String, Object> findNoticeById(Integer nid) {
    return Map.of("notice", noticeDAO.getNoticeById(nid)
                , "attaches", noticeDAO.getAttaches(nid));
  }

  @Override
  public boolean addNotice(NoticeDTO notice, List<MultipartFile> files) {
    
    try {

      //----- 공지사항 DB에 등록하기
      System.out.println("공지사항 등록 이전 nid : " + notice.getNid());  //----- 0
      int addedNoticeCount = noticeDAO.insertNotice(notice);  //----------------------- INSERT 수행하면서 파라미터 notice의 nid 필드에 자동 생성된 PK 값이 저장됩니다.
      System.out.println("공지사항 등록 이후 nid : " + notice.getNid());  //----- 자동 생성된 PK 값
      
      if (addedNoticeCount == 1) {        
        for (MultipartFile file : files) {
          //----- 첨부파일 서버에 저장하기
          if (!file.isEmpty()) {
            //----- 첨부 파일을 저장할 경로
            String filePath = fileUtil.getFilePath();
            //----- 디렉터리 없으면 생성하기
            Path uploadPath = Paths.get(filePath);
            if (Files.notExists(uploadPath)) {
              Files.createDirectories(uploadPath);
            }
            //----- 첨부 파일의 원래 이름
            String originalFilename = file.getOriginalFilename();
            //----- 첨부 파일을 서버에 저장할 때 사용하는 이릅
            String filesystemName = fileUtil.getFilesystemName(originalFilename);
            //----- 첨부 파일을 서버에 저장
            file.transferTo(Paths.get(filePath + "/" + filesystemName));
            //----- 첨부파일 정보 DB에 등록하기
            AttachDTO attach = AttachDTO.builder()
                .nid(notice.getNid())
                .filePath(filePath)
                .originalFilename(originalFilename)
                .filesystemName(filesystemName)
                .build();
            int addedAttachResult = noticeDAO.insertAttach(attach);
            if (addedAttachResult == 0) {
              return false;
            }
          }
        }
        
        return true;
      } else {
        return false;
      }
      
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
    
  }

  @Override
  public boolean deleteNotice(Integer nid) {
    //----- 삭제하려는 공지사항에 등록된 첨부 목록 가져오기
    List<AttachDTO> attaches = noticeDAO.getAttaches(nid);
    //----- 첨부 목록 참조해서 서버에 저장된 첨부 파일들을 삭제하기
    attaches.stream()
        .map(attach -> new File(attach.getFilePath(), attach.getFilesystemName()))
        .filter(file -> file.exists())  //----- 메소드 참조 방식의 코드 .filter(File::exists)
        .forEach(file -> {
          boolean deleted = file.delete();
          if (!deleted) {
            System.out.println("===== 파일 삭제 실패 : " + file.getPath());
          }
        });
    /*
    for (AttachDTO attach : attaches) {
      String path = attach.getFilePath() + "/" + attach.getFilesystemName();
      File file = new File(path);
      if (file.exists()) {
        boolean deleted = file.delete();
        if (!deleted) {
          System.out.println("===== 파일 삭제 실패 : " + path);
        }
      }
    }
    */
    //----- DB에서 공지사항 삭제하기 (ON DELETE CASCADE에 의해서 첨부 목록은 함께 삭제됩니다.)
    return noticeDAO.deleteNoticeById(nid) == 1;
  }

}
