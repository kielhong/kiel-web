package net.kiel.app.datetime

import java.text.SimpleDateFormat;

import spock.lang.Specification;

class DateTimeTest extends Specification{

    def "shouldGetAfterOneHour"() {
        setup:
        TimeZone seoul = TimeZone.getTimeZone("Asia/Seoul")
        Calendar date = Calendar.getInstance()
        date.set(1988, Calendar.MAY, 7, 23, 0)
        String pattern = "yyyy.MM.dd HH:mm"
        SimpleDateFormat format = new SimpleDateFormat(pattern)
        format.setTimeZone(seoul)
        
        when:
        def theTime = format.format(date.getTime())
        
        then:
        theTime == "1988.05.07 23:00"
        
        when:
        date.add(Calendar.HOUR_OF_DAY, 1)
        def after1Hour = format.format(date.getTime())
        
        then:
        seoul.inDaylightTime(date.getTime()) == true
        after1Hour == "1988.05.08 01:00"
        
    }
}
