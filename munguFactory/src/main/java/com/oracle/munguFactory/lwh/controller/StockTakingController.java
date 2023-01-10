package com.oracle.munguFactory.lwh.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.oracle.munguFactory.dto.ItemDTO;
import com.oracle.munguFactory.dto.StockPaging;
import com.oracle.munguFactory.dto.StockTakingDTO;
import com.oracle.munguFactory.lwh.service.StockTakingService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping(value = "/stocktaking")
@RequiredArgsConstructor
public class StockTakingController {

	private final StockTakingService st;
	
	@GetMapping(value = "/main")
	public String goStockTaking(Model model, StockTakingDTO stockTakingDTO) {
		
		//공장 코드
		model.addAttribute("factoryList", st.selectFactoryList());
		//상품 코드
		model.addAttribute("itemList", st.selectItemList(stockTakingDTO));
		
		model.addAttribute("subulList", st.selectSubulList());
		
		return "stocktaking/stocktaking";
	}
	
	@ResponseBody
	@GetMapping(value = "/search")
	public Map<String,Object> selectStockTakingList(StockTakingDTO stockTakingDTO) {
		
		Map<String,Object> resultMap = new HashMap<String, Object>();
		
		int totalStockTakingCnt = st.totalStockTakingCnt();
		
		StockPaging page = new StockPaging(totalStockTakingCnt, stockTakingDTO.getCurrentPage());
		
		stockTakingDTO.setStart(page.getStart());
		stockTakingDTO.setRowPage(page.getRowPage());
		
		resultMap.put("stockTakingList", st.selectStockTakingList(stockTakingDTO));
		resultMap.put("page", page);
		//목록 검색
		return resultMap;
	}
	
	
	@ResponseBody
	@GetMapping(value = "/itemSelect")
	public List<ItemDTO> selectItemList(StockTakingDTO stockTakingDTO) {
		return st.selectItemList(stockTakingDTO);
	}
	
	@ResponseBody
	@GetMapping(value = "/selectItemInfo")
	public List<StockTakingDTO> selectItemInfo(StockTakingDTO stockTakingDTO){
		return st.selectItemInfo(stockTakingDTO);
	}
	 
	//등록
	@PostMapping(value = "/insertStockTaking")
	public String insertStockTaking(StockTakingDTO stockTakingDTO) {
		int result = st.insertStockTaking(stockTakingDTO);
		return "redirect:/stocktaking/main";
	}
}