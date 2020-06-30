package com.bit.house.controller;

import com.bit.house.domain.AllMemberVO;
import com.bit.house.domain.BasketVO;
import com.bit.house.domain.MemberVO;
import com.bit.house.domain.ProductVO;
import com.bit.house.mapper.AdminMapper;
import com.bit.house.mapper.BasketMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

@Controller
public class BasketController {
    private HttpServletRequest request;


    @Autowired
    AdminMapper adminMapper;

    @Autowired
    BasketMapper basketMapper;



    @GetMapping("/goBasket")
    public String cart(String MemberId, Model model,AllMemberVO allMemberVO, BasketVO basketVO) {
        System.out.println("goBasket!");
        //UserId 있으면 (html에서 세션 검증)
        // basketVO = MemberService.getMemberBasket(MemberId);
        List<AllMemberVO> allMemberVOList = adminMapper.getMember();
        //System.out.println(allMemberVO.getUserid());
        System.out.println(allMemberVOList);
        model.addAttribute("allMemberVOList", allMemberVOList);
        return "nonMemberBasket";
    }


    //test
    @GetMapping("givePoint")
    public String givePoint(){
        return "th/admin/statAdmin/givePoint";
    }

    //productDetail
    @GetMapping("/productDetailsLJH")
    public String product(/*HttpSession session,String productName, String colorCode,int qty*/) {
        return "th/main/productDetailsLJH";
    }

    @GetMapping("/basketPop")
    public String basketPop(HttpServletRequest request) {
        return "th/member/basket/basketPop";
    }

    @RequestMapping(value = "/basketMember", method = RequestMethod.POST)
    public @ResponseBody void basketMem(BasketVO basketVO, String memberId,String productNo, String productColor, String qty){
        System.out.println("id : " + memberId + "pNo : "+productNo + "productColor : " + productColor
        + "Qty : " + qty);
        System.out.println("parse" +
                Integer.parseInt(qty)
        );
        basketVO.setMemberId(memberId);
        basketVO.setProductNo(productNo);
        basketVO.setProductColor(productColor);
        basketVO.setQty(Integer.parseInt(qty));
        basketMapper.insertBasketMember(basketVO);

    }

    @RequestMapping(value = "/basketLocal", method = RequestMethod.POST)
    public @ResponseBody void basketLocal(String[] hoho,HttpSession session, String first) {
        System.out.println("호호 출력"+hoho.getClass()+ "호호길이 : ");
        String[] ab = new String[100];
        int i=0;
        String check = "첫번째";
        System.out.println(hoho.length+"&&");
        System.out.println(first+"입니다"+check+"?"+first.equals(check));

        if(first.equals(check)==true){
            System.out.println("firest는 참입니다");
            List<String> proNo = new ArrayList<>();
            List<String> proColor = new ArrayList<>();
            List<Integer> proQty = new ArrayList<>();
            for (String a : hoho) {

                ab[i] = a;
                i++;
                System.out.println(a);
            }
            proNo.add(ab[0]);
            proColor.add(ab[1]);
            proQty.add(Integer.parseInt(ab[2]));
            session.setAttribute("proNo", proNo);
            session.setAttribute("proColor", proColor);
            session.setAttribute("proQty", proQty);
        }else {
            for (String a : hoho) {

                ab[i] = a;
                i++;
                System.out.println(a);
            }
            List<String> proNo = new ArrayList<>();
            List<String> proColor = new ArrayList<>();
            List<Integer> proQty = new ArrayList<>();
            int j;
            for (j = 0; j < i; j++) {
                System.out.println("ab?:" + ab[j]);
                proNo.add(ab[j].split(",")[0]);
                proColor.add(ab[j].split(",")[1]);
                proQty.add(Integer.parseInt(ab[j].split(",")[2]));
            }
            for (int b = 0; b < j; b++) {
                System.out.println(proNo.get(b));
                System.out.println(proColor.get(b));
                System.out.println(proQty.get(b));
            }
            session.setAttribute("proNo", proNo);
            session.setAttribute("proColor", proColor);
            session.setAttribute("proQty", proQty);
        }

    }

   /* @RequestMapping(value = "/basketSession", method = RequestMethod.POST)
    public @ResponseBody void basketSession(HttpSession session, String[] hoho) { // 상품 detail에서 장바구니 저장 누르면 ajax로 session arr에 있는 장바구니 리스트 받아옴 ->
        System.out.println("ajax!");
        for(String a : hoho){
            System.out.println("호호 출력 ArrayList");
            System.out.println(a);
        }
        // 비회원 장바구니 시작
        //String[] ajax = request.getParameterValues("hoho");
        String[] han;
        String[] hoho2 = new String[100];
        String[] split;
        List<String> hoho3 = new ArrayList<>();
        int count = 0;
        int k=0;
        int arrNum = 0;
        System.out.println("호호 길이 :: "+ hoho.length);


       // System.out.println("split : " + split);
        for (String string2 : hoho) {
            split = hoho[arrNum].split("[\"\\[\\]]");
            for (String string : split) {
                System.out.print("k : " + k);
                System.out.print(" " + string);
                System.out.println("");
                //System.out.println("in Null? : " +string.isEmpty());
                if (string.isEmpty() == false) {
                    System.out.println("hoho" + k + " 저장합니다 : " + string);
                    hoho2[count] = string;
                    hoho3.add(string);
                    System.out.println("저장된 값 ? :  "+ hoho2[k]);
                    count++;
                    System.out.println(count);
                }
                k++;
            }
            arrNum++;
        }
        System.out.println("hoho2 : " );
        //hoho2는 배열 hoho에 기본 길이가 100으로 돼있어서 hoho2에 다시 저장
        for(int i=0; i<count; i++){
            System.out.println(hoho2[i]);

        }
        System.out.println(Arrays.toString(hoho2));
        List<String> list2 = Arrays.asList(hoho2);
        System.out.println("리스트 전체 출력 : " + hoho3.toString());
        System.out.println("hoho3 출력");
        //hoho3는 리스트
        for(String item : hoho3){
            System.out.print(item);
        }
        System.out.println("setAttribute");
        session.setAttribute("hoho3", hoho3);
        String hohoSession = "";
        List<String> hohoSession2 = new ArrayList<>();
        System.out.println("getAttribute");
        hohoSession2 = (List<String>) session.getAttribute("hoho3");
        System.out.println("호호세션 : "+hohoSession2);

    }
*/

    @GetMapping("/basket")
    public String gobasket(HttpSession session,ProductVO productVO,BasketVO basketVO,Model model){

        MemberVO memberVO = (MemberVO) session.getAttribute("memberVO");
        String memberId = "";
        if(memberVO != null) {
            memberId = memberVO.getMemberId();
            System.out.println("memID : " + memberId);
        }
        if(memberId == "") { // 장바구니 클릭시 세션에 저장하고 클라이언트에 저장하기 때문에 그 외의 값은 들어가지 않는다
            List<String> hohoSession1 = new ArrayList<>();
            List<String> hohoSession2 = new ArrayList<>();
            List<Integer> hohoSession3 = new ArrayList<>();

            hohoSession1 = (List<String>) session.getAttribute("proNo");
            hohoSession2 = (List<String>) session.getAttribute("proColor");
            hohoSession3 = (List<Integer>) session.getAttribute("proQty");

            if (hohoSession2 == null) {
                System.out.println("값이 없다");

                return "th/member/basket/basketNull";
            } else {
                //if(userId == null){
                System.out.println("값이 있다");
                List<BasketVO> basketVOList = basketMapper.getNonMemberBasketList(hohoSession1,hohoSession2);
                for(int i=0; i<basketVOList.size(); i++) {
                    basketVOList.get(i).setQty(hohoSession3.get(i));
                    System.out.println("리스트 i 의 qty입니다"+basketVOList.get(i).getQty());
                }
                System.out.println("리스트:"+basketVOList);
                model.addAttribute("basketList", basketVOList);
                return "th/member/basket/nonMemberBasket";
                //}else{ model.add~ ~~ ~~  return "th/member/basket/basketMember";}
            }
        }else{ // 회원
            System.out.println("회원입니다");
            List<BasketVO> basketMember = basketMapper.getMemberBasketList(memberId);
            if(basketMember != null) {
                System.out.println("basketMem : " + basketMember);
                model.addAttribute("basketList2", basketMember);
                return "th/member/basket/memberBasket";
            }else{
                return "th/member/basket/basketNull";
            }
        }
    }



    @GetMapping("/btest")
    public String btest(){
        return "th/member/basket/btest";
    }

    /*@RequestMapping(method = RequestMethod.POST, value = "/delCheckBox")
    public @ResponseBody void delCheckBox(String[] delProductNoArray){
       basketMapper.deletememberBasket(delProductNoArray);

    }*/


    @RequestMapping(method = RequestMethod.POST, value = "/delNonmemberBasket")
    public @ResponseBody void delNonmemberBasket(String bool,HttpSession session){
        System.out.println("bool : " + bool);
        if(bool != null){
            session.removeAttribute("proNo");
            session.removeAttribute("proColor");
            session.removeAttribute("proQty");
        }
    }
    @RequestMapping(method = RequestMethod.POST, value = "/delMemberBasket")
    public @ResponseBody void delMemberBasket(String[] productNo, String[] productColor,HttpSession session){

        String memberId = null;

        MemberVO memberVO = (MemberVO) session.getAttribute("memberVO");
        List<BasketVO> basketVOList = new ArrayList<>(productNo.length);


        if(memberVO != null){
            memberId = memberVO.getMemberId();
            for(int i=0; i<productNo.length;i++){
                System.out.println("productNo : " + productNo[i] + "productColor : " + productColor[i]);
                BasketVO basketVO = new BasketVO(memberId,productNo[i],productColor[i]);
                basketVOList.add(basketVO);

            }
        }
        System.out.println("basketList :: " +basketVOList);

        basketMapper.deletememberBasket(basketVOList);
    }



}
