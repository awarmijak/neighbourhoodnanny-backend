package pl.sdacademy.neighbourhoodnanny;

import org.springframework.stereotype.Component;
import pl.sdacademy.neighbourhoodnanny.childcareevent.*;
import pl.sdacademy.neighbourhoodnanny.babysitter.*;
import pl.sdacademy.neighbourhoodnanny.child.*;
import pl.sdacademy.neighbourhoodnanny.location.*;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Component
public class DbInit {
    private final BabysitterRepository babysitterRepository;
    private final ChildRepository childRepository;
    private final ChildCareEventRepository childCareEventRepository;
    private final LocationRepository locationRepository;

    public DbInit(BabysitterRepository babysitterRepository, ChildRepository childRepository, ChildCareEventRepository childCareEventRepository, LocationRepository locationRepository) {
        this.babysitterRepository = babysitterRepository;
        this.childRepository = childRepository;
        this.childCareEventRepository = childCareEventRepository;
        this.locationRepository = locationRepository;
    }

    @PostConstruct
    public void onInit() {
        Child child1 = new Child("Jaś", "Buczek", LocalDate.of(2008,11,23));
        Child child2 = new Child("Kunegunda", "Buczek", LocalDate.of(2011,5,11));
        Child child3 = new Child("Krzyś", "Gagucki", LocalDate.of(2014,1,30));
        childRepository.save(child1);
        childRepository.save(child2);
        childRepository.save(child3);

        Babysitter babysitter1 = new Babysitter(
                "Pawel", "Buczek", "123456789", "pawel.buczek@email.com"
        );
        Babysitter babysitter2 = new Babysitter(
                "Kasia", "Gagucka", "123123123", "example@email.com"
        );
        babysitter1.addChild(child1);
        babysitter1.addChild(child2);
        babysitter2.addChild(child3);
        babysitterRepository.save(babysitter1);
        babysitterRepository.save(babysitter2);

        Location location1 = new Location("Jemiolowa 17", "Wroclaw", "00-000");
        Location location2 = new Location("Glowna 5a", "Katowice", "11-111");
        locationRepository.save(location1);
        locationRepository.save(location2);

        ChildCareEvent childCareEvent1 = new ChildCareEvent("nauka u Buczków",
                LocalDateTime.of(2020, 12, 18, 10, 0, 0),
                LocalDateTime.of(2020, 12, 18, 16, 30, 0),
                location1);
        childCareEvent1.addChild(child1);
        childCareEvent1.addChild(child3);
        ChildCareEvent childCareEvent2 = new ChildCareEvent("nauka u Buczków",
                LocalDateTime.of(2020, 12, 20, 10, 0, 0),
                LocalDateTime.of(2020, 12, 20, 16, 30, 0),
                location1);
        childCareEvent2.addChild(child2);
        ChildCareEvent childCareEvent3 = new ChildCareEvent("zabawa w Katowicach",
                LocalDateTime.of(2020, 12, 25, 8, 0, 0),
                LocalDateTime.of(2020, 12, 28, 19, 0, 0),
                location2);
        childCareEvent3.addChild(child1);
        childCareEvent3.addChild(child2);
        childCareEvent3.addChild(child3);
        childCareEventRepository.save(childCareEvent1);
        childCareEventRepository.save(childCareEvent2);
        childCareEventRepository.save(childCareEvent3);
    }
}