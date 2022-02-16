import org.springframework.data.repository.CrudRepository;

@Repository
public interface WordRepository extends CrudRepository<Word, String> {
    Word findByName(String name);
}
