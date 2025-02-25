package com.example.cv;

import com.melkassib.cvgenerator.altacv.domain.*;
import com.melkassib.cvgenerator.altacv.utils.PredefinedColorPalette;
import com.melkassib.cvgenerator.common.domain.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Set;

import static com.melkassib.cvgenerator.common.domain.EventPeriodDate.eventDurationDate;
import static com.melkassib.cvgenerator.common.domain.EventPeriodString.eventDurationStr;
import static com.melkassib.cvgenerator.common.utils.ResumeHelper.firstColumn;
import static com.melkassib.cvgenerator.common.utils.ResumeHelper.secondColumn;

public class AltaCVMain {

    public static void main(String[] args) throws IOException {
        final var tagline = "Your Position or Tagline Here";
        final var photo = new Photo(2.8, "Globe_High.png");
        final var userInfo = new AltaCVUserInfo("Your Name Here", Set.of(
                new Email("your_name@email.com"),
                new Phone("000-00-0000"),
                new MailAddress("Address, Street, 00000 Country"),
                new Location("Location, COUNTRY"),
                new HomePage("www.homepage.com"),
                new Twitter("@twitterhandle"),
                new LinkedIn("your_id"),
                new Github("your_id"),
                new Orcid("0000-0000-0000-0000"),
                new UserInfoField("gitlab", "\\faGitlab", "https://gitlab.com/", "your_id")
        ));

        final var config = new AltaCVConfig(0.6, PhotoShape.NORMAL, PredefinedColorPalette.THEME3);
        final var header = new AltaCVHeader(tagline, userInfo, photo);

        // --------------------- Experience ---------------------

        final var s1Event1 = new Event();
        s1Event1.setTitle("Job Title 1");
        s1Event1.setHolder("Company 1");
        s1Event1.setLocation("Location");
        s1Event1.setDuration(eventDurationStr("Month XXXX", "Ongoing"));
        s1Event1.setDescription(List.of(
                new Item("Job description 1"),
                new Item("Job description 2"),
                new Item("Job description 3", false)
        ));

        final var s1Event2 = new Event();
        s1Event2.setTitle("Job Title 2");
        s1Event2.setHolder("Company 2");
        s1Event2.setLocation("Location");
        s1Event2.setDuration(eventDurationDate("2023-10", "2023-10"));
        s1Event2.setDescription(List.of(new Item("Item1")));

        final var s1Event3 = new Event();
        s1Event3.setTitle("Job Title 3");
        s1Event3.setHolder("Company 2");

        final var section1 = new Section("Experience", firstColumn(1), List.of(
                s1Event1,
                Divider.INSTANCE,
                s1Event2,
                Divider.INSTANCE,
                s1Event3
        ));

        // --------------------- Projects ---------------------

        final var s2Event1 = new Event();
        s2Event1.setTitle("Project 1");
        s2Event1.setHolder("Funding agency/institution");
        s2Event1.setDescription(List.of(new Item("Details")));

        final var s2Event2 = new Event();
        s2Event2.setTitle("Project 2");
        s2Event2.setHolder("Funding agency/institution");
        s2Event2.setDuration(eventDurationStr("Project duration"));
        s2Event2.setDescription(List.of(new Item(" A short abstract would also work.", false)));

        final var section2 = new Section("Projects", firstColumn(2), List.of(
                s2Event1,
                Divider.INSTANCE,
                s2Event2
        ));

        // --------------------- A day of my life ---------------------

        final var section3 = new Section("A day of my life", firstColumn(3), List.of(
            new WheelChart(1.5, 0.5, List.of(
                new WheelChartItem(6, 8, "accent!30", "Sleep,\\\\beautiful sleep"),
                new WheelChartItem(3, 8, "accent!40", "Hopeful novelist by night"),
                new WheelChartItem(8, 8, "accent!60", "Daytime job"),
                new WheelChartItem(2, 10, "accent", "Sports and relaxation"),
                new WheelChartItem(5, 8, "accent!20", "Spending time with family")
            )),
            NewPage.INSTANCE
        ));

        // --------------------- My Life Philosophy ---------------------

        final var section4 = new Section("My Life Philosophy", secondColumn(1), List.of(
            new Quote("Something smart or heartfelt, preferably in one sentence.")
        ));

        // --------------------- Most Proud of ---------------------

        final var section5 = new Section("Most Proud of", secondColumn(2), List.of(
                new Achievement("faTrophy", "Fantastic Achievement", "and some details about it"),
                new Achievement("faHeartbeat", "Another achievement", "more details about it of course"),
                new Achievement("faHeartbeat", "Another achievement", "more details about it of course")
        ));

        // --------------------- Strengths ---------------------

        final var section6 = new Section("Strengths", secondColumn(3), List.of(
            new Tag("Hard-working"),
            new Tag("Eye for detail"),
            NewLine.INSTANCE,
            new Tag("Motivator & Leader"),
            Divider.INSTANCE,
            new Tag("C++"),
            new Tag("Embedded Systems"),
            NewLine.INSTANCE,
            new Tag("Statistical Analysis")
        ));

        // --------------------- Languages ---------------------

        final var section7 = new Section("Languages", secondColumn(4), List.of(
            new SkillStr("Arabic", "Native/Bilingual"),
            new SkillStr("English", "Professional working proficiency"),
            new SkillStr("Spanish", "Limited working proficiency"),
            new Skill("German", 2.0)
        ));

        // --------------------- Education ---------------------

        final var s8Event1 = new Event();
        s8Event1.setTitle("Ph.D. in Your Discipline");
        s8Event1.setHolder("Your University");
        s8Event1.setDuration(eventDurationDate("2002-09", "2006-06"));
        s8Event1.setDescription(List.of(new Item("Thesis title: Wonderful Research",  false)));

        final var s8Event2 = new Event();
        s8Event2.setTitle("M.Sc. in Your Discipline");
        s8Event2.setHolder("Your University");
        s8Event2.setDuration(eventDurationDate("2001-09", "2002-06"));

        final var s8Event3 = new Event();
        s8Event3.setTitle("B.Sc. in Your Discipline");
        s8Event3.setHolder("Stanford University");
        s8Event3.setDuration(eventDurationDate("1998-09", "2001-06"));

        final var section8 = new Section("Education", secondColumn(5), List.of(
                s8Event1,
                Divider.INSTANCE,
                s8Event2,
                Divider.INSTANCE,
                s8Event3
        ));

        // --------------------- Resume ---------------------

        final var sections = List.of(section1, section2, section3, section4, section5, section6, section7, section8);
        final var resume = new AltaCVResume(config, header, sections);

        final var resumeAsLaTeX = resume.toLaTeX();
        final var resumeAsJson = resume.toJson();

        // Print resume to console
        System.out.println(resume.toLaTeX());
        System.out.println(resume.toJson());

        // Print resume to files
        Files.writeString(Path.of("my-altacv-resume.tex"), resumeAsLaTeX);
        Files.writeString(Path.of("my-altacv-resume.json"), resumeAsJson);
    }

}
