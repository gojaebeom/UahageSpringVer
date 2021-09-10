package com.uahage.api.dto;

import com.uahage.api.domain.user.User;
import com.uahage.api.domain.user.UserBaby;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Slf4j
public class UserJoinRequest{
    @Setter
    private String email;
    private String nickname;
    private Short ageGroupType;
    private List<Character> babyGenders;
    private List<String> babyBirthdays;

    public void verifyAllState() {
        this.verifyEmail();
        this.verifyNickname();
        this.verifyAgeGroupType();
        this.verifyBabyGender();
    }

    public void verifyEmail() {
        log.info("[이메일 유효성 검사]");
        log.info(this.email);
        if(this.email == null){
            throw new IllegalArgumentException("이메일이 입력되지 않았습니다.");
        }
        log.info("[이메일 Null 검사 : Pass]");

        final String PATTERN = "^([\\w\\.\\_\\-])*[a-zA-Z0-9]+([\\w\\.\\_\\-])*([a-zA-Z0-9])+([\\w\\.\\_\\-])+@([a-zA-Z0-9]+\\.)+[a-zA-Z0-9]{2,8}$";
        String email = "";
        try{
            email = this.email.split(":")[1];
        }catch(Exception e){
            throw new IllegalArgumentException("소셜 구분이 정의되지 않았습니다.");
        }

        if(!email.matches(PATTERN)){
            throw new IllegalArgumentException("이메일 형식이 아닙니다.");
        }
        log.info("[이메일 정규식 검사 : Pass]");
    }

    public void verifyNickname() {
        log.info("[닉네임 유효성 검사]");
        log.info(this.nickname);
        final String PATTERN = "!/^[a-zA-Zㄱ-힣0-9]*$/";
        final String[] WORDS = {"ㄱ", "ㄴ", "ㄷ", "ㄹ", "ㅁ", "ㅂ", "ㅅ", "ㅇ", "ㅈ", "ㅊ", "ㅋ", "ㅌ", "ㅍ", "ㅎ", "ㅏ", "ㅑ", "ㅓ", "ㅕ", "ㅗ", "ㅛ", "ㅜ", "ㅠ", "ㅡ", "ㅣ", "ㅢ", "ㅔ", "ㅖ"};
        final String SLANG_STRINGS = "10새,10새기,10새리,10세리,10쉐이,10쉑,10스,10쌔,10쌔기,10쎄,10알,10창,10탱,18것,18넘,18년,18노,18놈,18뇬,18럼,18롬,18새,18새끼,18색,18세끼,18세리,18섹,18쉑,18스,18아,ㄱㅐ,ㄲㅏ,ㄲㅑ,ㄲㅣ,ㅅㅂㄹㅁ,ㅅㅐ,ㅆㅂㄹㅁ,ㅆㅍ,ㅆㅣ,ㅆ앙,ㅍㅏ,凸,갈보,갈보년,강아지,같은년,같은뇬,개같은,개구라,개년,개놈,개뇬,개대중,개독,개돼중,개랄,개보지,개뻥,개뿔,개새,개새기,개새끼,개새키,개색기,개색끼,개색키,개색히,개섀끼,개세,개세끼,개세이,개소리,개쑈,개쇳기,개수작,개쉐,개쉐리,개쉐이,개쉑,개쉽,개스끼,개시키,개십새기,개십새끼,개쐑,개씹,개아들,개자슥,개자지,개접,개좆,개좌식,개허접,걔새,걔수작,걔시끼,걔시키,걔썌,걸레,게색기,게색끼,광뇬,구녕,구라,구멍,그년,그새끼,냄비,놈현,뇬,눈깔,뉘미럴,니귀미,니기미,니미,니미랄,니미럴,니미씹,니아배,니아베,니아비,니어매,니어메,니어미,닝기리,닝기미,대가리,뎡신,도라이,돈놈,돌아이,돌은놈,되질래,뒈져,뒈져라,뒈진,뒈진다,뒈질, 뒤질래,등신,디져라,디진다,디질래,딩시,따식,때놈,또라이,똘아이,똘아이,뙈놈,뙤놈,뙨넘,뙨놈,뚜쟁,띠바,띠발,띠불,띠팔,메친넘,메친놈,미췬,미췬,미친,미친넘,미친년,미친놈,미친새끼,미친스까이,미틴,미틴넘,미틴년,미틴놈,바랄년,병자,뱅마,뱅신,벼엉신,병쉰,병신,부랄,부럴,불알,불할,붕가,붙어먹,뷰웅,븅,븅신,빌어먹,빙시,빙신,빠가,빠구리,빠굴,빠큐,뻐큐,뻑큐,뽁큐,상넘이,상놈을,상놈의,상놈이,새갸,새꺄,새끼,새새끼,새키,색끼,생쑈,세갸,세꺄,세끼,섹스,쇼하네,쉐,쉐기,쉐끼,쉐리,쉐에기,쉐키,쉑,쉣,쉨,쉬발,쉬밸,쉬벌,쉬뻘,쉬펄,쉽알,스패킹,스팽,시궁창,시끼,시댕,시뎅,시랄,시발,시벌,시부랄,시부럴,시부리,시불,시브랄,시팍,시팔,시펄,신발끈,심발끈,심탱,십8,십라,십새,십새끼,십세,십쉐,십쉐이,십스키,십쌔,십창,십탱,싶알,싸가지,싹아지,쌉년,쌍넘,쌍년,쌍놈,쌍뇬,쌔끼,쌕,쌩쑈,쌴년,썅,썅년,썅놈,썡쇼,써벌,썩을년,썩을놈,쎄꺄,쎄엑,쒸벌,쒸뻘,쒸팔,쒸펄,쓰바,쓰박,쓰발,쓰벌,쓰팔,씁새,씁얼,씌파,씨8, 씨끼,씨댕,씨뎅,씨바,씨바랄,씨박,씨발,씨방,씨방새,씨방세,씨밸,씨뱅,씨벌,씨벨,씨봉,씨봉알,씨부랄,씨부럴,씨부렁,씨부리,씨불,씨붕,씨브랄,씨빠,씨빨,씨뽀랄,씨앙,씨파,씨팍,씨팔,씨펄,씸년,씸뇬,씸새끼,씹같,씹년,씹뇬,씹보지,씹새,씹새기,씹새끼,씹새리,씹세,씹쉐,씹스키,씹쌔,씹이,씹자지,씹질,씹창,씹탱,씹퇭,씹팔,씹할,씹헐,아가리,아갈,아갈이,아갈통,아구창,아구통,아굴,얌마,양넘,양년,양놈,엄창,엠병,여물통,염병,엿같,옘병,옘빙,오입,왜년,왜놈,욤병,육갑,은년,을년,이년,이새끼,이새키,이스끼,이스키,임마,자슥,잡것,잡넘,잡년,잡놈,저년,저새끼,접년,젖밥,조까,조까치,조낸,조또,조랭,조빠,조쟁이,조지냐,조진다,조찐,조질래,존나,존나게,존니,존만,존만한,좀물,좁년,좆,좁밥,좃까,좃또,좃만,좃밥,좃이,좃찐,좆같,좆까,좆나,좆또,좆만,좆밥,좆이,좆찐,좇같,좇이,좌식,주글,주글래,주데이,주뎅,주뎅이,주둥아리,주둥이,주접,주접떨,죽고잡,죽을래,죽통,쥐랄,쥐롤,쥬디,지랄,지럴,지롤,지미랄,짜식,짜아식,쪼다,쫍빱,찌랄,창녀,캐년,캐놈,캐스끼,캐스키,캐시키,탱구,팔럼,퍽큐,호로,호로놈,호로새끼,호로색,호로쉑,호로스까이,호로스키,후라들,후래자식,후레,후뢰,씨ㅋ발,ㅆ1발,씌발,띠발,띄발,뛰발,띠ㅋ발,뉘뮈";
        final String[] SLANG_LIST = SLANG_STRINGS.split(",");

        if(this.nickname == null) {
            throw new IllegalArgumentException("닉네임이 입력되지 않았습니다.");
        }
        log.info("[닉네임 Null 검사 : PASS ]");
        for (String word : WORDS)
            if (this.nickname.contains(word))
                throw new IllegalArgumentException("올바르지 않은 형식입니다.");
        log.info("[닉네임 자음, 모음 따로 사용 검사 : PASS ]");

        if (Pattern.matches(PATTERN, this.nickname))
            throw new IllegalArgumentException("닉네임에 공백 또는 특수문자을 사용할 수 없습니다.");
        log.info("[닉네임 정규식 검사 : PASS ]");

        if (this.nickname.length() < 2 || this.nickname.length() > 12)
            throw new IllegalArgumentException("닉네임은 2-12자리 사이로 입력해주세요.");
        log.info("[닉네임 길이 검사 : PASS ]");

        for (String slang : SLANG_LIST)
            if (this.nickname.contains(slang))
                throw new IllegalArgumentException("비속어를 포함할 수 없습니다.");
        log.info("[닉네임 비속어 검사 : PASS ]");
    }

    public void verifyAgeGroupType() {
        log.info("[부모 연령층 유효성 검사]");
        log.info(this.ageGroupType.toString());

        if(ageGroupType == null){
            this.ageGroupType = 6;
        }
        log.info("[부모 연령층 Null 검사 : PASS ]");
        if(1 > ageGroupType || ageGroupType > 6){
            throw new IllegalArgumentException("AGE_GROUP_TYPE 은 1 ~ 6 사이의 값만 포함할 수 있습니다.");
        }
        log.info("[부모 연령층 입력값 검사 : PASS ]");
    }

    public void verifyBabyGender() {
        log.info("[아이 성별 검사 ]");
        if(this.babyGenders == null){
            this.babyGenders.add('M');
        }
        log.info("[아이 성별 Null 검사 : PASS ]");

        for(Character babyGender : this.babyGenders){
            log.info(babyGender.toString());

            if(babyGender.equals('M') || babyGender.equals('F')) { } else {
                throw new IllegalArgumentException("BABY_GENDER 는 'F' 또는 'M' 값만 포함할 수 있습니다.");
            }
            log.info("[아이 성별 입력값 검사 : PASS ]");
        }
    }

    public User toUser() {
        return User.builder()
                .email(this.email)
                .nickname(this.nickname)
                .ageGroupType(this.ageGroupType)
                .build();
    }

    public List<UserBaby> toUserBabies(User user) {
        List<UserBaby> userBabies = new ArrayList<>();

        for(int i = 0; i < this.babyGenders.size(); i++){
            UserBaby userBaby = UserBaby.builder()
                    .user(user)
                    .babyBirthday(this.babyBirthdays.get(i))
                    .babyGender(this.babyGenders.get(i))
                    .build();
            userBabies.add(userBaby);
        }
        return userBabies;
    }


}
