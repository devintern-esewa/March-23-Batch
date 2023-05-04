package np.com.esewa.learn.librarymanagementsystem.entities.transactions;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import np.com.esewa.learn.librarymanagementsystem.entities.Book;
import np.com.esewa.learn.librarymanagementsystem.entities.User;
import np.com.esewa.learn.librarymanagementsystem.entities.enums.TransactionStatus;
import org.springframework.data.annotation.Reference;

/**
 * @author SatyaRajAwasth1 on 4/26/2023
 * @project LibraryManagementSystem
 */

@Getter
@Setter
@Entity(name = "user-borrow-return-book")
public class UserBorrowReturnBook {
    @Id
    @Column(name = "transaction_id")
    private long id;

    @OneToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(referencedColumnName = "isbn",name = "book_isbn_number")
    private Book book;

    @OneToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(referencedColumnName = "users_id",name = "users_id")
    private User user;

    @Column(name = "transaction_status")
    private TransactionStatus transactionStatus;

}
