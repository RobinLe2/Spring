package org.shark.ioc.ex01_xml;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor  //----- Calculator() : 디폴트 생성자
@AllArgsConstructor  //---- Calculator(String, int, Adder, Subtractor, Multiplier, Divider)
@Getter
@Setter
@ToString
public class Calculator {
  private String brand;
  private int price;
  private Adder adder;
  private Subtractor subtractor;
  private Multiplier multiplier;
  private Divider divider;
}
