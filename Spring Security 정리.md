# Spring Security 스켈레톤 프로젝트 정리
## 코드 기능
### 회원가입, 로그인
회원가입을 하면 비밀번호를 암호화하여 DB에 저장한다.

로그인에 성공하면 회원DB를 조작할 수 있는 접근토큰을 발급받는다.

### 회원 목록 조회, 회원 단일 조회, 회원 정보 수정, 회원 삭제
Header에 발급받은 토큰을 담아야 해당 기능들을 사용할 수 있다.

## Spring Security 스켈레톤 정리 문헌
https://github.com/upswp/SOLAFY_2.0.0/wiki/Spring-Boot-Security
