package com.example.cv

import com.melkassib.cvgenerator.altacv.domain.*
import com.melkassib.cvgenerator.altacv.utils.PredefinedColorPalette
import com.melkassib.cvgenerator.common.domain.Divider
import com.melkassib.cvgenerator.common.domain.EventPeriodDate.Companion.eventDurationDate
import com.melkassib.cvgenerator.common.domain.EventPeriodString.Companion.eventDurationStr
import com.melkassib.cvgenerator.common.domain.Item
import com.melkassib.cvgenerator.common.domain.NewLine
import com.melkassib.cvgenerator.common.utils.firstColumn
import com.melkassib.cvgenerator.common.utils.secondColumn
import java.io.File

fun main() {
    val resume =
        altacv {
            config {
                photoShape = PhotoShape.NORMAL
                theme = PredefinedColorPalette.THEME3
            }

            header {
                tagline = "Your Position or Tagline Here"
                photo = Photo(2.8, "Globe_High.png")
                userInfo = AltaCVUserInfo("Your Name Here", setOf(
                    Email("your_name@email.com"),
                    Phone("000-00-0000"),
                    MailAddress("Address, Street, 00000 Country"),
                    Location("Location, COUNTRY"),
                    HomePage("www.homepage.com"),
                    Twitter("@twitterhandle"),
                    LinkedIn("your_id"),
                    Github("your_id"),
                    Orcid("0000-0000-0000-0000"),
                    UserInfoField("gitlab", "\\faGitlab", "https://gitlab.com/", "your_id")
                ))
            }

            sections {
                section("Experience", firstColumn(1), Divider) {
                    contents {
                        event("Job Title 1") {
                            holder = "Company 1"
                            location = "Location"
                            duration = eventDurationStr("Month XXXX", "Ongoing")
                            description = listOf(
                                Item("Job description 1"),
                                Item("Job description 2"),
                                Item("Job description 3", false)
                            )
                        }

                        event("Job Title 2") {
                            holder = "Company 2"
                            location = "Location"
                            duration = eventDurationDate("2023-10", "2023-10")
                            description = listOf(Item("Item1"))
                        }

                        event("Job Title 3") {
                            holder = "Company 3"
                            location = "Location"
                        }
                    }
                }

                section("Projects", firstColumn(2), separator = Divider) {
                    contents {
                        event("Project 1") {
                            holder = "Funding agency/institution"
                            description = listOf(Item("Details"))
                        }

                        event("Project 1") {
                            holder = "Funding agency/institution"
                            duration = eventDurationStr("Project duration")
                            description = listOf(Item(" A short abstract would also work.", withBullet = false))
                        }
                    }
                }

                section("A day of my life", firstColumn(3)) {
                    contents {
                        wheelchart(1.5, 0.5) {
                            item(6, 8, "accent!30", "Sleep,\\\\beautiful sleep")
                            item(3, 8, "accent!40", "Hopeful novelist by night")
                            item(8, 8, "accent!60", "Daytime job")
                            item(2, 10, "accent", "Sports and relaxation")
                            item(5, 8, "accent!20", "Spending time with family")
                        }
                        content("\\newpage")
                    }
                }

                section("My Life Philosophy", secondColumn(1)) {
                    contents {
                        quote("Something smart or heartfelt, preferably in one sentence.")
                    }
                }

                section("Most Proud of", secondColumn(2), ignored = false) {
                    contents {
                        achievement("faTrophy", "Fantastic Achievement", "and some details about it")
                        achievement("faHeartbeat", "Another achievement", "more details about it of course")
                        achievement("faHeartbeat", "Another achievement", "more details about it of course")
                    }
                }

                section("Strengths", secondColumn(3)) {
                    contents {
                        tag("Hard-working")
                        tag("Eye for detail")
                        content(NewLine)

                        tag("Motivator & Leader")
                        content(Divider)

                        tag("C++")
                        tag("Embedded Systems")
                        content(NewLine)

                        tag("Statistical Analysis")
                    }
                }

                section("Languages", secondColumn(4)) {
                    contents {
                        skill("Arabic", "Native/Bilingual")
                        skill("English", "Professional working proficiency")
                        skill("Spanish", "Limited working proficiency")
                        skill("German", 2.0)
                    }
                }

                section("Education", secondColumn(5), separator = Divider) {
                    contents {
                        event("Ph.D. in Your Discipline") {
                            holder = "Your University"
                            duration =  eventDurationDate("2002-09", "2006-06")
                            description = listOf(Item("Thesis title: Wonderful Research",  false))
                        }

                        event("M.Sc. in Your Discipline") {
                            holder = "Your University"
                            duration = eventDurationDate("2001-09", "2002-06")
                        }

                        event("B.Sc. in Your Discipline") {
                            holder = "Stanford University"
                            duration = eventDurationDate("1998-09", "2001-06")
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
    File("my-altacv-resume.tex").writeText(resumeAsLaTeX)
    File("my-altacv-resume.json").writeText(resumeAsJson)
}