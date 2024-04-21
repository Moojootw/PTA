package sisims.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Data
@Table(name = "transaction_items")
public class TransactionItems {
    @EmbeddedId
    private TransactionItemId id;

    @ManyToOne
    @MapsId("transactionId")
    @JoinColumn(name = "transaction_id")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Transaction transaction;

    @ManyToOne
    @MapsId("itemId")
    @JoinColumn(name = "item_id")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Item item;
    
    @SuppressWarnings("serial")
    @Embeddable
    @Data
    public static class TransactionItemId implements Serializable {
        @Column(name = "transaction_id")
        private Long transactionId;
        @Column(name = "item_id")
        private Long itemId;
        
        public TransactionItemId() {
        }
        public TransactionItemId(Long transactionId, Long itemId) {
            this.transactionId = transactionId;
            this.itemId = itemId;
        }
    }
    
}


