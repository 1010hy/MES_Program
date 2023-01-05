package com.oracle.munguFactory.dto;

import java.sql.Date;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class ProdDTO {
	
	private int      sujuNo;       //수주번호
	private int      itemNo;       //품번
	private int      empNo;        //사원번호
	private Date     finishDate;   //생산완료일자
	private Date     sujuDate;     //수주일자
	private int      prodAmount;   //생산량
	private String   prodState;    //진행상태
	private int      factoryCode;  //공장코드

	//페이징
	private Long   rn;		
	private String pageNum;  
	private int    start; 		 	
	private int    end;


}