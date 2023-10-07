package com.relise.data

import com.example.data.R
import com.relise.domain.HomeModel
import com.relise.domain.QuestionsModel
import com.relise.domain.QuizModel
import com.relise.domain.User
import com.relise.repository.SportRepository

class SportRepositoryMock():SportRepository {

    private val userList: MutableList<User> = mutableListOf()

    override fun getHome(): List<HomeModel> {
        return listOf(
            HomeModel(R.drawable.img_soccer, "Football", "3"),
            HomeModel(R.drawable.img_basketball, "Basketball", "2"),
            HomeModel(R.drawable.img_boxing, "Boxing", "2"),
            HomeModel(R.drawable.img_golf, "Golf", "1"),
            HomeModel(R.drawable.img_tennis, "Tennis", "1"),
        )
    }

    override fun getQuiz(): List<QuizModel> {
        return listOf(

            //Golf
            QuizModel(R.drawable.ic_like, "Golf",
                "Aces and Eagles", "5"),

            //Tennis
            QuizModel(R.drawable.ic_like, "Tennis",
                "Grand Slam Champions", "5"),

            //Boxing

            QuizModel(R.drawable.ic_like, "Boxing",
                "Boxing History", "5"),

            //Football
            QuizModel(R.drawable.ic_like, "Football",
                "World Cup Winners", "5"),
            QuizModel(R.drawable.ic_like, "Football",
                "Football Legends", "5"),
            QuizModel(R.drawable.ic_like, "Football",
                "Famous Goals", "5"),

            //Basketball
            QuizModel(R.drawable.ic_like, "Basketball",
                "NBA Superstars", "5"),
            QuizModel(R.drawable.ic_like, "Basketball",
                "Basketball History", "4")
        )
    }

    override fun getQuestions(): List<QuestionsModel> {
        return listOf(

            // Grand Slam Champions
            QuestionsModel("Grand Slam Champions (Golf)", "Who is often referred to as one of the greatest golfers of all time and has won the most major championships?",
                listOf("Tiger Woods", "Jack Nicklaus", "Arnold Palmer", "Phil Mickelson"), "1"),
            QuestionsModel("Grand Slam Champions (Golf)", "Which golfer is known for achieving the career Grand Slam by winning all four major championships in golf?",
                listOf("Rory McIlroy", "Jordan Spieth", "Gary Player", "Ben Hogan"), "2"),
            QuestionsModel("Grand Slam Champions (Golf)", "Who holds the record for the most Masters Tournament victories?",
                listOf("Tiger Woods", "Arnold Palmer", "Sam Snead", "Tom Watson"), "0"),
            QuestionsModel("Grand Slam Champions (Golf)", "Which golfer is known as the 'Golden Bear' and has the most major wins in golf history?",
                listOf("Arnold Palmer", "Tom Watson", "Gary Player", "Jack Nicklaus"), "3"),
            QuestionsModel("Grand Slam Champions (Golf)", "Who was the youngest golfer to win all four major championships in a career?",
                listOf("Jordan Spieth", "Tiger Woods", "Rory McIlroy", "Jack Nicklaus"), "1"),


            // Tennis
            QuestionsModel("Grand Slam Champions", "Who is often referred to as 'The Greatest' and is considered one of the greatest tennis players of all time?",
                listOf("Roger Federer", "Rafael Nadal", "Novak Djokovic", "Serena Williams"), "0"),
            QuestionsModel("Grand Slam Champions", "Which tennis player is known for winning the 'Golden Slam' by winning all four Grand Slam tournaments and an Olympic gold medal in a single year?",
                listOf("Steffi Graf", "Serena Williams", "Andre Agassi", "Novak Djokovic"), "0"),
            QuestionsModel("Grand Slam Champions", "Who holds the record for the most Wimbledon singles titles in tennis history?",
                listOf("Pete Sampras", "Bjorn Borg", "Roger Federer", "Martina Navratilova"), "2"),
            QuestionsModel("Grand Slam Champions", "Which tennis player is known for his incredible speed and agility on the court, earning him the nickname 'The Flash'?",
                listOf("Rafael Nadal", "Roger Federer", "Novak Djokovic", "Gael Monfils"), "3"),
            QuestionsModel("Grand Slam Champions", "Who is the only tennis player to achieve the calendar-year Grand Slam in the Open Era (winning all four Grand Slam titles in a single calendar year)?",
                listOf("Rod Laver", "Bjorn Borg", "Rafael Nadal", "Steffi Graf"), "0"),



            // Boxing History
            QuestionsModel("Boxing History", "Who is often referred to as 'The King' and is considered one of the greatest boxers of all time?",
                listOf("Muhammad Ali", "Mike Tyson", "George Foreman", "Joe Frazier"), "0"),
            QuestionsModel("Boxing History", "Which boxer is known for his famous 'Phantom Punch' knockout of Sonny Liston in 1965?",
                listOf("Joe Louis", "Evander Holyfield", "George Foreman", "Muhammad Ali"), "1"),
            QuestionsModel("Boxing History", "Who holds the record for the most consecutive heavyweight title defenses in boxing history?",
                listOf("Larry Holmes", "Mike Tyson", "Rocky Marciano", "Lennox Lewis"), "3"),
            QuestionsModel("Boxing History", "Which boxer famously won a gold medal at the 1976 Olympics and later became a world champion in multiple weight classes?",
                listOf("Sugar Ray Leonard", "Manny Pacquiao", "Marvin Hagler", "Floyd Mayweather Jr."), "2"),
            QuestionsModel("Boxing History", "Who is known as 'The Hitman' and was a dominant welterweight and middleweight champion?",
                listOf("Thomas Hearns", "Roberto Durán", "Julio César Chávez", "Oscar De La Hoya"), "3"),


            //World Cup Winners
            QuestionsModel("World Cup Winners", "Which country has won the most FIFA World Cups?",
                listOf("Brazil", "Germany", "Italy", "Argentina"), "0"),
            QuestionsModel("World Cup Winners", "In which year was the first FIFA World Cup held?",
                listOf("1930", "1954", "1978", "1998"), "3"),
            QuestionsModel("World Cup Winners", "Who is the all-time leading goal scorer in FIFA World Cup history?",
                listOf("Miroslav Klose", "Pele", "Diego Maradona", "Cristiano Ronaldo"), "2"),
            QuestionsModel("World Cup Winners", "Which country hosted the 2018 FIFA World Cup?",
                listOf("Russia", "France", "Brazil", "Germany"), "0"),
            QuestionsModel("World Cup Winners", "Who won the Golden Boot award in the 2014 FIFA World Cup?",
                listOf("James Rodriguez", "Lionel Messi", "Neymar", "Thomas Muller"), "1"),

            //Football Legends
            QuestionsModel("Football Legends", "Who is often referred to as 'The King' and is a Brazilian football legend?",
                listOf("Pele", "Maradona", "Zinedine Zidane", "Cristiano Ronaldo"), "0"),
            QuestionsModel("Football Legends", "Which footballer is known for his 'Hand of God' goal in the 1986 World Cup?",
                listOf("Diego Maradona", "Lionel Messi", "George Best", "Ronaldinho"), "0"),
            QuestionsModel("Football Legends", "Who is the top scorer in the history of the UEFA Champions League?",
                listOf("Cristiano Ronaldo", "Lionel Messi", "Raul", "Robert Lewandowski"), "0"),
            QuestionsModel("Football Legends", "Which Italian defender is known for his exceptional defending skills and was a part of the 'Italian Job' defense?",
                listOf("Paolo Maldini", "Franco Baresi", "Fabio Cannavaro", "Alessandro Nesta"), "2"),
            QuestionsModel("Football Legends", "Which former English footballer is famous for his 'Beckham Bend' free-kick technique?",
                listOf("David Beckham", "Steven Gerrard", "Frank Lampard", "Paul Scholes"), "0"),

            //Famous Goals
            QuestionsModel("Famous Goals", "Which player scored the 'Hand of God' goal and the 'Goal of the Century' in the 1986 World Cup?",
                listOf("Diego Maradona", "Pele", "Johan Cruyff", "Franz Beckenbauer"), "0"),
            QuestionsModel("Famous Goals", "Who scored the winning goal for England in the 1966 World Cup final against West Germany?",
                listOf("Geoff Hurst", "Bobby Charlton", "Alan Ball", "Martin Peters"), "1"),
            QuestionsModel("Famous Goals", "Which Brazilian forward scored a stunning bicycle kick goal against Bayer Leverkusen in the UEFA Champions League final?",
                listOf("Zinedine Zidane", "Cristiano Ronaldo", "Rivaldo", "Ronaldinho"), "2"),
            QuestionsModel("Famous Goals", "Who scored the iconic 'Agüerooooo' goal to win the Premier League for Manchester City in 2012?",
                listOf("Sergio Agüero", "David Silva", "Yaya Toure", "Vincent Kompany"), "3"),
            QuestionsModel("Famous Goals", "Which Dutch striker scored a memorable goal against Argentina in the 1998 World Cup with a superb turn and volley?",
                listOf("Marco van Basten", "Dennis Bergkamp", "Ruud Gullit", "Robin van Persie"), "0"),


            //NBA Superstars
            QuestionsModel("NBA Superstars", "Who is often referred to as 'The King' and is considered one of the greatest basketball players of all time?",
                listOf("Michael Jordan", "LeBron James", "Kobe Bryant", "Magic Johnson"), "1"),
            QuestionsModel("NBA Superstars", "Which NBA player is known for his famous 'Skyhook' shot?",
                listOf("Shaquille O'Neal", "Larry Bird", "Kareem Abdul-Jabbar", "Tim Duncan"), "2"),
            QuestionsModel("NBA Superstars", "Who holds the record for the most points scored in a single NBA game?",
                listOf("Kobe Bryant", "Wilt Chamberlain", "Michael Jordan", "James Harden"), "1"),
            QuestionsModel("NBA Superstars", "Which NBA team has won the most championships in league history?",
                listOf("Los Angeles Lakers", "Chicago Bulls", "Boston Celtics", "Golden State Warriors"), "0"),
            QuestionsModel("NBA Superstars", "Who is known as 'The Answer' and was a prolific scorer for the Philadelphia 76ers?",
                listOf("Shaquille O'Neal", "Allen Iverson", "Dwyane Wade", "Tracy McGrady"), "1"),


            //Basketball History
            QuestionsModel("Basketball History", "Which NBA player is known for his famous 'Skyhook' shot?",
                listOf("Shaquille O'Neal", "Larry Bird", "Kareem Abdul-Jabbar", "Tim Duncan"), "2"),
            QuestionsModel("Basketball History", "Who holds the record for the most points scored in a single NBA game?",
                listOf("Kobe Bryant", "Wilt Chamberlain", "Michael Jordan", "James Harden"), "1"),
            QuestionsModel("Basketball History", "Which NBA team has won the most championships in league history?",
                listOf("Los Angeles Lakers", "Chicago Bulls", "Boston Celtics", "Golden State Warriors"), "0"),
            QuestionsModel("Basketball History", "Who is known as 'The Answer' and was a prolific scorer for the Philadelphia 76ers?",
                listOf("Shaquille O'Neal", "Allen Iverson", "Dwyane Wade", "Tracy McGrady"), "1"),

            )


    }

    override fun getUser(): List<User> {
        return userList // Возвращаем список пользователей

    }

    override fun addUser(user: User) {
        userList.add(user)
    }

    override fun updateUser(user: User) {
        val existingUser = userList.find { it.username == user.username }
        existingUser?.let {
            it.complitedTest = user.complitedTest
            it.rating = user.rating
        }
    }
}