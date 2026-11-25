package li.nexus.nexus;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import li.nexus.nexus.entity.Cliente;
import li.nexus.nexus.repository.ClienteRepository;

@SpringBootTest
class NexusApplicationTests {

	@Test
	Cliente isActive(ClienteRepository repo, Long deletedId) {
		return repo.findById(deletedId).get();
	}

}
