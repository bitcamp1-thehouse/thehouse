# 니가사는그집

김하현, 김민종, 이중훈, 남윤대, 정올레그

’오늘의집‘을 벤치마킹한 인테리어 쇼핑몰 및 커뮤니티(일반회원, 업체, 관리자 중점으로 기능 분담)

-스프링 시큐리티/Oauth2를 활용한 소셜 로그인

-딥러닝을 이용한 상품추천(DL4J, word2vec)

-상품 : 상품 랜덤 무한스크롤, 검색엔진(자동완성), 카테고리사이드바, 상품상세페이지

-일반회원 : 장바구니저장(회원-DB/비회원-세션), 카카오페이, 이니시스 API이용하여 결제, 쪽지함, 
프로필관리(사진/스크랩/팔로우/팔로잉 리스트), 마이쇼핑(주문내역, 리뷰작성/삭제, 반품/취소), 회원정보수정, 상담원과 1:1채팅

-커뮤니티 : sns같은 사진 게시판(다중이미지 업로드, 무한스크롤, 댓글/팔로우/스크랩/좋아요), 질문게시판(계층형 게시판, 네이버에디터, 페이징, 답글/수정/삭제/댓글)

-업체 : 정보 수정, 상품관리(등록, 수정), 고객 주문리스트 관리(배송처리, 검색)

-관리자 : 회원소비통계, 회원/등록상품 정보 확인, 채팅대기중인 고객리스트, 채팅이력확인, 업체 정보확인, 업체신청 승인


사용기술
- Front-End :　HTML5, CSS3, JavaScript, JQuery, JQuery UI, AJAX, Chart.js, Bootstrap, Stomp.js

- Back-End : Java, Spring Boot 2.3.0, Spring-Security, Websocket, Mybatis, gradle, Thymeleaf, MySQL, redis, DL4J(딥러닝)

- Open Api : 카카오페이 API, 이니시스 API, Naver Smart Editor

- Tool : IntelliJ, GitHub, eXerd, starUML


주의사항
-추천트레이닝파일에 없는 상품 클릭후 메인페이지로 나올시 500에러 ex)신규상품, 상품모델명 수정, 상품삭제시
-->오류발생시 clickproduct테이블에서 해당 상품 삭제하면 해결됨
-->트레이닝을 원할경우 상품 등록후 clickproduct 테이블에 비슷한 상품이랑 함께 넣어 데이터 만든후 관리자-회원관리-추천트레이닝 접속후 트레이닝하기 클릭하면 해결됨.