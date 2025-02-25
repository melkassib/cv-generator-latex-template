package com.example.cv;

import com.melkassib.cvgenerator.awesomecv.domain.*;
import com.melkassib.cvgenerator.common.domain.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.LinkedHashSet;
import java.util.List;

import static com.melkassib.cvgenerator.common.domain.EventPeriodString.eventDurationStr;
import static com.melkassib.cvgenerator.common.utils.ResumeHelper.firstColumn;

public class AwesomeCVMain {

    public static void main(String[] args) throws IOException {
        final var photo = new Photo(PhotoShape.RECTANGLE, PhotoEdge.NO_EDGE, PhotoDirection.RIGHT, "profile");
        final var quote = "Be the change that you want to see in the world.";

        final var personalInfo = new LinkedHashSet<>(
                List.of(
                    new Position("Developer \\enskip\\cdotp\\enskip Cloud Engineer"),
                    new MailAddress("Address, Street, City"),
                    new Phone("(+212) 000-000-000"),
                    new Email("contact@email.com"),
                    new HomePage("www.homepage.com"),
                    new Github("your_id"),
                    new LinkedIn("your_id"),
                    new Gitlab("your_id"),
                    new Twitter("@your_id"),
                    new Skype("your_id"),
                    new Reddit("your_id"),
                    new Medium("your_id"),
                    new StackOverFlow("SO-id", "SO-name"),
                    new GoogleScholar("googlescholar-id", "name-to-display"),
                    new ExtraInfo("extra information")
                )
        );

        final var userInfo = new AwesomeCVUserInfo("John", "Dupont", personalInfo);

        final var config = new AwesomeCVConfig(ColorTheme.ORANGE, true, "\\cdotp");
        final var header = new AwesomeCVHeader(HeaderAlignment.CENTER, userInfo, photo, quote);
        final var footer = new AwesomeCVFooter("\\today", "John Dupont $\\sim$$\\sim$ $\\cdot$ $\\sim$$\\sim$ Résumé", "\\thepage");

        // --------------------- Summary ---------------------

        final var paragraph = new Paragraph(
    """
            Nulla blandit sapien ligula, sit amet rutrum urna scelerisque a. Fusce malesuada eros erat, eget porttitor enim elementum a.
            Mauris maximus metus massa, accumsan convallis ligula molestie ut. Sed nulla nibh, venenatis vitae bibendum malesuada, porta venenatis ex.
            In hac habitasse platea dictumst.
            """
        );

        final var section1 = new Section("Summary", firstColumn(1), List.of(paragraph));

        // --------------------- Work Experience ---------------------

        final var s2Event1 = new Event();
        s2Event1.setTitle("Software Engineer");
        s2Event1.setHolder("TechNova Solutions");
        s2Event1.setLocation("New York, USA");
        s2Event1.setDuration(eventDurationStr("Jan. 2022", "Present"));
        s2Event1.setDescription(List.of(
                new Item("Developed scalable microservices architecture for cloud applications."),
                new Item("Reduced system downtime by 40% by implementing automated monitoring and alerting."),
                new Item("Led a team of 5 engineers to build a real-time analytics dashboard using Kafka and Elasticsearch.")
        ));

        final var s2Event2 = new Event();
        s2Event2.setTitle("Backend Developer");
        s2Event2.setHolder("CodeWave Inc.");
        s2Event2.setLocation("San Francisco, USA");
        s2Event2.setDuration(eventDurationStr("Jun. 2019", "Dec. 2021"));
        s2Event2.setDescription(List.of(
            new Item("Designed RESTful APIs that handled over 1M requests per day."),
            new Item("Optimized database queries, improving response times by 50%."),
            new Item("Implemented CI/CD pipelines using Jenkins and Docker to streamline deployments.")
        ));

        final var section2 = new Section("Work Experience", firstColumn(2), List.of(s2Event1, s2Event2));

        // --------------------- Honors & Awards ---------------------

        final var honorList1 = new HonorList("International Awards", List.of(
            new HonorItem("Winner", "Google Cloud Hackathon", "Online", "2022"),
            new HonorItem("Top 5 Finalist", "Microsoft AI Challenge", "Seattle, USA", "2021")
        ));

        final var honorList2 = new HonorList("Domestic Awards", List.of(
            new HonorItem("1st Place", "National Coding Championship", "New York, USA", "2020")
        ));
        final var section3 = new Section("Honors & Awards", firstColumn(3), List.of(
            honorList1,
            new LatexContent("\\medskip"),
            Divider.INSTANCE,
            honorList2
        ));

        // --------------------- Resume ---------------------

        final var resume = new AwesomeCVResume(config, header, footer, List.of(section1, section2, section3));

        final var resumeAsLaTeX = resume.toLaTeX();
        final var resumeAsJson = resume.toJson();

        // Print resume to console
        System.out.println(resume.toLaTeX());
        System.out.println(resume.toJson());

        // Print resume to files
        Files.writeString(Path.of("my-awesomecv-resume.tex"), resumeAsLaTeX, StandardOpenOption.CREATE);
        Files.writeString(Path.of("my-awesomecv-resume.json"), resumeAsJson, StandardOpenOption.CREATE);
    }

}
