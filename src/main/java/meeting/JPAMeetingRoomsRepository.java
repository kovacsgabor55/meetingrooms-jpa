package meeting;

import lombok.AllArgsConstructor;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

@AllArgsConstructor
public class JPAMeetingRoomsRepository implements MeetingRoomsRepository {

    private final EntityManagerFactory entityManagerFactory;

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
        EntityManager em = entityManagerFactory.createEntityManager();
        List<MeetingRoom> resultList = em.createQuery("select m from MeetingRoom m order by m.name asc", MeetingRoom.class)
                .getResultList();
        em.close();
        return resultList;
    }

    @Override
    public List<MeetingRoom> getExactMeetingRoomByName(String name) {
        EntityManager em = entityManagerFactory.createEntityManager();
        List<MeetingRoom> resultList = em.createQuery("select m from MeetingRoom m where m.name like :name order by m.name asc", MeetingRoom.class)
                .setParameter("name", name)
                .getResultList();
        em.close();
        return resultList;
    }

    @Override
    public List<MeetingRoom> getMeetingRoomsByPrefix(String nameOrPrefix) {
        EntityManager em = entityManagerFactory.createEntityManager();
        List<MeetingRoom> resultList = em.createQuery("select m from MeetingRoom m where m.name like :name order by m.name asc", MeetingRoom.class)
                .setParameter("name", nameOrPrefix + "%")
                .getResultList();
        em.close();
        return resultList;
    }

    @Override
    public void deleteAll() {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.createQuery("delete from MeetingRoom");
        em.close();
    }
}
