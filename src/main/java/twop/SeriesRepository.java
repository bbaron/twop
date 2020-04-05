package twop;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

interface SeriesRepository extends JpaRepository<Series, Long> {
  Optional<Series> findByName(String email);
}
