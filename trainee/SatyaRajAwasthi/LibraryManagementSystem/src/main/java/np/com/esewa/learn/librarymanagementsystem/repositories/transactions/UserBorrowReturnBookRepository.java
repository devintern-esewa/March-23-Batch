package np.com.esewa.learn.librarymanagementsystem.repositories.transactions;

import np.com.esewa.learn.librarymanagementsystem.entities.transactions.UserBorrowReturnBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Thinkpad on 4/26/2023
 * @project LibraryManagementSystem
 */
@Repository
public interface UserBorrowReturnBookRepository extends JpaRepository<UserBorrowReturnBook, Long> {
}
