package kr.ror.controller.cron;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component; // cron 스케줄러 클래스임을 선언하는 어노테이션.

import kr.ror.controller.RecipeController;

@Component
public class Scheduler { // cron 문법으로 스케줄러가 실행되는 주기를 설정.(아래의 cron 스케줄 문법에 자세한 설명)
    //private static final Logger LOGGER = LoggerFactory.getLogger(RecipeController.class);
    
    // * * * * * * *
    // 초 분 시 일 월 요일 년도(생략가능 - 생략시에는 매년의 의미)
    // 요일의 경우는 1부터 7의 수를 사용하는데 1:일 2:월 3:화 4:수 5:목 6:금 7:토
    // 「0 0 10 1 1 1」 지정을 하게 되면 매년(생략됨) 1월 1일 10시 00분 00초의 월요일
    // 「0,10 0 10 * * *」이라고 표현식으로 지정을 하게 되면 매년(생략됨) 매월 매일 10시 00분 0초와 10초에 실행
    // 초 위치에 0/10라고 하면 10초 마다라는 의미
    // 1-10이라고 하면 1일부터 10일까지라는 의미
    // L : 마지막날이라는 의미 - 일과 요일에 사용
    // W : 일에서만 사용되는데 지정된 가장 가까운 평일을 찾는다.
    // # : 요일에서만 사용되는데 주#요일을 의미. 2#2라고 하면 두번째 주 월요일

    // fixedDealy는 이전에 실행된 Task의 종료시간으로 부터 정의된 시간만큼 지난 후 Task를 실행한다.(밀리세컨드 단위)
    // fixedRate는 이전에 실행된 Task의 시작시간으로 부터 정의된 시간만큼 지난 후 Task를 실행한다.(밀리세컨드 단위)
    // @Scheduled(fixedDelay=10000)

//    @PostConstruct
//    @Scheduled(cron = "0/10 * * * * *")
//    public void scheduler() throws Exception {
//        LocalDateTime vLocalDateTime = LocalDateTime.now();
//        DateTimeFormatter vDateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
//
//        System.out.println("스케쥴러 : " + vDateTimeFormatter.format(vLocalDateTime));
//    }

}
