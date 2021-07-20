package meeting;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mariadb.jdbc.MariaDbDataSource;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JPAMeetingRoomsRepositoryTest {

    private JPAMeetingRoomsRepository jpaMeetingRoomsRepository;

    @BeforeEach
    void init() throws SQLException {
        MariaDbDataSource dataSource;
        dataSource = new MariaDbDataSource();
        dataSource.setUrl("jdbc:mariadb://localhost:3306/meetingrooms?useUnicode=true");
        dataSource.setUser("meetingrooms");
        dataSource.setPassword("meetingrooms");

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("pu");
        jpaMeetingRoomsRepository = new JPAMeetingRoomsRepository(entityManagerFactory);
    }

    @Test
    void save() {
        MeetingRoom meetingRoom = jpaMeetingRoomsRepository.save("gfd", 2, 6);

        String result = jpaMeetingRoomsRepository.getMeetingroomsOrderedByName().get(0);
        assertEquals("gfd", result);
    }

    @Test
    void getMeetingroomsOrderedByName() {
        for (int i = 5; i > 0; i--) {
            jpaMeetingRoomsRepository.save("gfd" + i, 2, 6);
        }

        List<String> results = jpaMeetingRoomsRepository.getMeetingroomsOrderedByName();
        assertEquals("gfd1", results.get(0));
        assertEquals("gfd5", results.get(4));
    }

    @Test
    void getEverySecondMeetingRoom() {
    }

    @Test
    void getMeetingRooms() {
    }

    @Test
    void getExactMeetingRoomByName() {
    }

    @Test
    void getMeetingRoomsByPrefix() {
    }

    @Test
    void deleteAll() {
    }
}