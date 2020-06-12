package com.bit.house.controller;

import com.bit.house.domain.ProductVO;
import com.bit.house.recommenderProcess.RecommendProcess;
import com.bit.house.recommenderProcess.TrainingProcess;
import com.bit.house.service.RecommenderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;
import java.util.Collection;
import java.util.List;

@Controller
public class RecommenderController {

    private static Logger log = LoggerFactory.getLogger(RecommenderController.class);

    @Autowired(required =false)
    RecommenderService recommenderService;

    //추천아이템 트레이닝 페이지
    @GetMapping("/training")
    public String getTraining(){

        TrainingProcess training=new TrainingProcess();
        training.training(recommenderService.selectClickProductById());

        return "th/admin/recommender/trainingTestPage";
    }

    //회원별 추천아이템
    @GetMapping("/recommenderItems")
    public String getRecommender(Model model, HttpSession session){ //추후 추천아이템이 보이는 페이지와 연결해야한다.

        /*String id = (String) session.getAttribute("memberId");

        String clickProduct=recommenderService.selectClickProduct(id);*/
        String clickProduct=recommenderService.selectClickProduct();
        log.info(clickProduct);
        System.out.println(clickProduct);

        if(clickProduct==null){//회원이 최근 조회한 아이템이 없으면 추천아이템 보여주지 않기
            model.addAttribute("showRecommend", null);
        } else{
            //회원이 최근 조회한 아이템이 있으면 추천시스템에 돌리기
            RecommendProcess recommend=new RecommendProcess();
            Collection<String> recommendNos=recommend.recommender(clickProduct);
            //추천받은 아이템 디테일 가져오기
            List<ProductVO> recommendItems=recommenderService.selectProductList(recommendNos);

            model.addAttribute("recommendList", recommendItems);
            model.addAttribute("showRecommend", clickProduct);
        }

        return "th/main/recommendList";
    }
}
