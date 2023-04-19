package me;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "emp")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long bookId;

    @NonNull
    private String name;

    @NonNull
    private String summary;

    private int rating;
}
