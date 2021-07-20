package meeting;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

public class JPAMeetingRoomsRepository implements MeetingRoomsRepository {

    private final EntityManagerFactory entityManagerFactory;

    public JPAMeetingRoomsRepository(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    @Override
    public MeetingRoom save(String name, int width, int length) {
        MeetingRoom meetingRoom = new MeetingRoom(name, width, length);

        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.persist(meetingRoom);
        em.getTransaction().commit();
        em.close();

        return meetingRoom;
    }

    @Override
    public List<String> getMeetingroomsOrderedByName() {
        EntityManager em = entityManagerFactory.createEntityManager();
        List<String> names = em.createQuery("select m.name from MeetingRoom m order by m.name asc", String.class)
                .getResultList();
        em.close();
        return names;
    }

    @Override
    public List<String> getEverySecondMeetingRoom() {
        return null;
    }

    @Override
    public List<MeetingRoom> getMeetingRooms() {
        return null;
    }

    @Override
    public List<MeetingRoom> getExactMeetingRoomByName(String name) {
        return null;
    }

    @Override
    public List<MeetingRoom> getMeetingRoomsByPrefix(String nameOrPrefix) {
        return null;
    }

    @Override
    public void deleteAll() {

    }
}