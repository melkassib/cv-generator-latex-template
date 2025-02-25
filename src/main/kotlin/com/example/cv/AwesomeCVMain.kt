package com.example.cv

import com.melkassib.cvgenerator.awesomecv.domain.*
import com.melkassib.cvgenerator.common.domain.Divider
import com.melkassib.cvgenerator.common.domain.EventPeriodString.Companion.eventDurationStr
import com.melkassib.cvgenerator.common.domain.Item
import java.io.File

fun main() {
    val resume =
        awesomecv {
            config {
                colorTheme = ColorTheme.ORANGE
                isSectionHighlighted = true
                headerSocialSeparator = "\\cdotp"
            }

            header {
                photo = Photo(PhotoShape.RECTANGLE, PhotoEdge.NO_EDGE, path = "profile")
                quote = "Be the change that you want to see in the world."

                user {
                    firstName = "John"
                    lastName = "Dupont"
                    personalInfo = linkedSetOf(
                        Position("Developer \\enskip\\cdotp\\enskip Cloud Engineer"),
                        MailAddress("Address, Street, City"),
                        Phone("(+212) 000-000-000"),
                        Email("contact@email.com"),
                        HomePage("www.homepage.com"),
                        Github("your_id"),
                        LinkedIn("your_id"),
                        Gitlab("your_id"),
                        Twitter("@your_id"),
                        Skype("your_id"),
                        Reddit("your_id"),
                        Medium("your_id"),
                        StackOverFlow("SO-id", "SO-name"),
                        GoogleScholar("googlescholar-id", "name-to-display"),
                        ExtraInfo("extra information"),
                    )
                }
            }

            footer {
                left = "\\today"
                center = "John Dupont $\\sim$$\\sim$ $\\cdot$ $\\sim$$\\sim$ Résumé"
                right = "\\thepage"
            }

            sections {
                section("Summary") {
                    contents {
                        paragraph(
                            """
                            Nulla blandit sapien ligula, sit amet rutrum urna scelerisque a. Fusce malesuada eros erat, eget porttitor enim elementum a.
                            Mauris maximus metus massa, accumsan convallis ligula molestie ut. Sed nulla nibh, venenatis vitae bibendum malesuada, porta venenatis ex.
                            In hac habitasse platea dictumst.
                            """.trimIndent()
                        )
                    }
                }

                section("Work Experience") {
                    contents {
                        event("Software Engineer") {
                            holder = "TechNova Solutions"
                            location = "New York, USA"
                            duration = eventDurationStr("Jan. 2022", "Present")
                            description = listOf(
                                Item("Developed scalable microservices architecture for cloud applications."),
                                Item("Reduced system downtime by 40% by implementing automated monitoring and alerting."),
                                Item("Led a team of 5 engineers to build a real-time analytics dashboard using Kafka and Elasticsearch.")
                            )
                        }

                        event("Backend Developer") {
                            holder = "CodeWave Inc."
                            location = "San Francisco, USA"
                            duration = eventDurationStr("Jun. 2019", "Dec. 2021")
                            description = listOf(
                                Item("Designed RESTful APIs that handled over 1M requests per day."),
                                Item("Optimized database queries, improving response times by 50%."),
                                Item("Implemented CI/CD pipelines using Jenkins and Docker to streamline deployments.")
                            )
                        }
                    }
                }

                section("Honors & Awards") {
                    contents {
                        honors("International Awards") {
                            honor("Winner", "Google Cloud Hackathon", "Online", "2022")
                            honor("Top 5 Finalist", "Microsoft AI Challenge", "Seattle, USA", "2021")
                        }

                        content("\\medskip")
                        content(Divider)

                        honors("Domestic Awards") {
                            honor("1st Place", "National Coding Championship", "New York, USA", "2020")
                        }
                    }
                }
            }
        }

    val resumeAsLaTeX = resume.toLaTeX()
    val resumeAsJson = resume.toJson()

    // Print resume to console
    println(resume.toLaTeX())
    println(resume.toJson())

    // Print resume to files
    File("my-awesomecv-resume.tex").writeText(resumeAsLaTeX)
    File("my-awesomecv-resume.json").writeText(resumeAsJson)
}