package net.kiel.web.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

import org.springframework.data.annotation.LastModifiedDate;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name="retailer")
public class Retailer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="retailer_id")
    @Getter @Setter
    private Integer retailerId;
    
    @Getter @Setter 
    private String name;
    
    @Getter @Setter 
    private String description;
    
    @Column(name="site_url")
    @Getter @Setter 
    private String siteUrl;
       
    @JsonProperty("auto_balance")
    @Column(name="auto_balance")
    @Getter @Setter 
    private Boolean autoBalance;
    
    @Column(name="amount_min")
    @Getter @Setter 
    private Integer amountMin;
    
    @Column(name="amount_max")
    @Getter @Setter 
    private Integer amountMax;
    
    @OneToMany(fetch=FetchType.LAZY)
    @JoinColumn(name="retailer_id")
    @Getter @Setter 
    private List<Amount> amounts;
    
    @Column(name="term_privacy")
    @Getter @Setter
    private String termPrivacy;
    
    @Column(name="created_date")
    @Getter @Setter 
    private Date createdDate;
    
    @LastModifiedDate
    @Column(name="updated_date")
    @Getter @Setter 
    private Date updatedDate;
    
    @Column(name="barcode_type")
    @Enumerated(EnumType.ORDINAL)
    @Getter @Setter 
    private BarCodeType barcodeType;
    
    // cashstar merchant_code
    @Column(name="merchant_code")
    @Getter @Setter 
    private String merchantCode;

    @Column(name="status")
    @Getter @Setter 
    private Boolean status;
    
    @Column(name="logo_image_url")
    @Getter @Setter
    private String logoImageUrl;

    @Column(name="image_url")
    @Getter @Setter
    private String imageUrl;
    
    @Column(name="image_v")
    @Getter @Setter
    private String image;
    
    @Column(name="image_v_small")
    @Getter @Setter
    private String imageSmall;
     
    public enum BarCodeType {
        /** Aztec 2D barcode format. */
        kBarcodeFormatAztec(0),
        /** CODABAR 1D format. */
        kBarcodeFormatCodabar(1),
        /** Code 39 1D format. */
        kBarcodeFormatCode39(2),
        /** Code 93 1D format. */
        kBarcodeFormatCode93(3),
        /** Code 128 1D format. */
        kBarcodeFormatCode128(4),
        /** Data Matrix 2D barcode format. */
        kBarcodeFormatDataMatrix(5),
        /** EAN-8 1D format. */
        kBarcodeFormatEan8(6),
        /** EAN-13 1D format. */
        kBarcodeFormatEan13(7),
        /** ITF (Interleaved Two of Five) 1D format. */
        kBarcodeFormatITF(8),
        /** MaxiCode 2D barcode format. */
        kBarcodeFormatMaxiCode(9),
        /** PDF417 format. */
        kBarcodeFormatPDF417(10),
        /** QR Code 2D barcode format. */
        kBarcodeFormatQRCode(11),
        /** RSS 14 */
        kBarcodeFormatRSS14(12),
        /** RSS EXPANDED */
        kBarcodeFormatRSSExpanded(13),
        /** UPC-A 1D format. */
        kBarcodeFormatUPCA(14),
        /** UPC-E 1D format. */
        kBarcodeFormatUPCE(15),
        /** UPC/EAN extension format. Not a stand-alone format. */
        kBarcodeFormatUPCEANExtension(16),
        /** No Bardcode */
        kBarcodeFormatNone(17);
        
        @Getter private final int value;
        
        BarCodeType(int value) {
            this.value = value;
        }
    }
    
    @PreUpdate
    private void preUpdate() {
        setUpdatedDate(new Date());
    }
}
