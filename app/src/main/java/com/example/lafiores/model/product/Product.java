package com.example.lafiores.model.product;

import android.os.Parcel;
import android.os.Parcelable;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.BindingAdapter;
import androidx.databinding.library.baseAdapters.BR;
import androidx.recyclerview.widget.DiffUtil;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.bumptech.glide.Glide;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;
@Entity(tableName = "products")
public class Product extends BaseObservable implements Parcelable {

    @SerializedName("id")
    @Expose
    @PrimaryKey
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("slug")
    @Expose
    private String slug;
    @SerializedName("permalink")
    @Expose
    private String permalink;
    @SerializedName("date_created")
    @Expose
    private String dateCreated;
    @SerializedName("date_created_gmt")
    @Expose
    private String dateCreatedGmt;
    @SerializedName("date_modified")
    @Expose
    private String dateModified;
    @SerializedName("date_modified_gmt")
    @Expose
    private String dateModifiedGmt;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("featured")
    @Expose
    private Boolean featured;
    @SerializedName("catalog_visibility")
    @Expose
    private String catalogVisibility;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("short_description")
    @Expose
    private String shortDescription;
    @SerializedName("sku")
    @Expose
    private String sku;
    @SerializedName("price")
    @Expose
    private String price;
    @SerializedName("regular_price")
    @Expose
    private String regularPrice;
    @SerializedName("sale_price")
    @Expose
    private String salePrice;
    @SerializedName("date_on_sale_from")
    @Expose
    @Ignore
    private Object dateOnSaleFrom;
    @SerializedName("date_on_sale_from_gmt")
    @Expose
    @Ignore
    private Object dateOnSaleFromGmt;
    @SerializedName("date_on_sale_to")
    @Expose
    @Ignore
    private Object dateOnSaleTo;
    @SerializedName("date_on_sale_to_gmt")
    @Expose
    @Ignore
    private Object dateOnSaleToGmt;
    @SerializedName("price_html")
    @Expose
    private String priceHtml;
    @SerializedName("on_sale")
    @Expose
    private Boolean onSale;
    @SerializedName("purchasable")
    @Expose
    private Boolean purchasable;
    @SerializedName("total_sales")
    @Expose
    private Integer totalSales;
    @SerializedName("virtual")
    @Expose
    private Boolean virtual;
    @SerializedName("downloadable")
    @Expose
    private Boolean downloadable;
    @SerializedName("downloads")
    @Expose
    @Ignore
    private List<Object> downloads = null;
    @SerializedName("download_limit")
    @Expose
    private Integer downloadLimit;
    @SerializedName("download_expiry")
    @Expose
    private Integer downloadExpiry;
    @SerializedName("external_url")
    @Expose
    private String externalUrl;
    @SerializedName("button_text")
    @Expose
    private String buttonText;
    @SerializedName("tax_status")
    @Expose
    private String taxStatus;
    @SerializedName("tax_class")
    @Expose
    private String taxClass;
    @SerializedName("manage_stock")
    @Expose
    private Boolean manageStock;
    @SerializedName("stock_quantity")
    @Expose
    @Ignore
    private Object stockQuantity;
    @SerializedName("stock_status")
    @Expose
    private String stockStatus;
    @SerializedName("backorders")
    @Expose
    private String backorders;
    @SerializedName("backorders_allowed")
    @Expose
    private Boolean backordersAllowed;
    @SerializedName("backordered")
    @Expose
    private Boolean backordered;
    @SerializedName("sold_individually")
    @Expose
    private Boolean soldIndividually;
    @SerializedName("weight")
    @Expose
    private String weight;
    @SerializedName("dimensions")
    @Expose
    @Ignore
    private Dimensions dimensions;
    @SerializedName("shipping_required")
    @Expose
    private Boolean shippingRequired;
    @SerializedName("shipping_taxable")
    @Expose
    private Boolean shippingTaxable;
    @SerializedName("shipping_class")
    @Expose
    private String shippingClass;
    @SerializedName("shipping_class_id")
    @Expose
    private Integer shippingClassId;
    @SerializedName("reviews_allowed")
    @Expose
    private Boolean reviewsAllowed;
    @SerializedName("average_rating")
    @Expose
    private String averageRating;
    @SerializedName("rating_count")
    @Expose
    private Integer ratingCount;
    @SerializedName("related_ids")
    @Expose
    @Ignore
    private List<Integer> relatedIds = null;
    @SerializedName("upsell_ids")
    @Expose
    @Ignore
    private List<Object> upsellIds = null;
    @SerializedName("cross_sell_ids")
    @Expose
    @Ignore
    private List<Object> crossSellIds = null;
    @SerializedName("parent_id")
    @Expose
    private Integer parentId;
    @SerializedName("purchase_note")
    @Expose
    private String purchaseNote;
    @SerializedName("categories")
    @Expose
    @Ignore
    private List<Category> categories = null;
    @SerializedName("tags")
    @Expose
    @Ignore
    private List<Object> tags = null;
    @SerializedName("lang")
    @Expose
    private String lang;
    @SerializedName("translations")
    @Expose
    @Ignore
    private Translations translations;
    @SerializedName("images")
    @Expose
    @Ignore
    private List<Image> images = null;

    @BindingAdapter({"mainImagePath"})
    public static void loadMainImage(ImageView imageView, List<Image> images) {
        if (images != null)
            Glide
                    .with(imageView)
                    .load(images.get(0).getSrc().split(".png")[0] + "-510x510.png")
//                .placeholder(R.drawable.ic_launcher_foreground)
                    .into(imageView);
    }

    @SerializedName("attributes")
    @Expose
    @Ignore
    private List<Attribute> attributes = null;
    @SerializedName("default_attributes")
    @Expose
    @Ignore
    private List<DefaultAttribute> defaultAttributes = null;
    @SerializedName("variations")
    @Expose
    @Ignore
    private List<Object> variations = null;
    @SerializedName("grouped_products")
    @Expose
    @Ignore
    private List<Object> groupedProducts = null;
    @SerializedName("menu_order")
    @Expose
    private Integer menuOrder;
    @SerializedName("meta_data")
    @Expose
    @Ignore
    private List<Object> metaData = null;
    @SerializedName("_links")
    @Expose
    @Ignore
    private Links links;
    public final static Parcelable.Creator<Product> CREATOR = new Creator<Product>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Product createFromParcel(Parcel in) {
            return new Product(in);
        }

        public Product[] newArray(int size) {
            return (new Product[size]);
        }

    };

    protected Product(Parcel in) {
        this.id = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.name = ((String) in.readValue((String.class.getClassLoader())));
        this.slug = ((String) in.readValue((String.class.getClassLoader())));
        this.permalink = ((String) in.readValue((String.class.getClassLoader())));
        this.dateCreated = ((String) in.readValue((String.class.getClassLoader())));
        this.dateCreatedGmt = ((String) in.readValue((String.class.getClassLoader())));
        this.dateModified = ((String) in.readValue((String.class.getClassLoader())));
        this.dateModifiedGmt = ((String) in.readValue((String.class.getClassLoader())));
        this.type = ((String) in.readValue((String.class.getClassLoader())));
        this.status = ((String) in.readValue((String.class.getClassLoader())));
        this.featured = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
        this.catalogVisibility = ((String) in.readValue((String.class.getClassLoader())));
        this.description = ((String) in.readValue((String.class.getClassLoader())));
        this.shortDescription = ((String) in.readValue((String.class.getClassLoader())));
        this.sku = ((String) in.readValue((String.class.getClassLoader())));
        this.price = ((String) in.readValue((String.class.getClassLoader())));
        this.regularPrice = ((String) in.readValue((String.class.getClassLoader())));
        this.salePrice = ((String) in.readValue((String.class.getClassLoader())));
        this.dateOnSaleFrom = ((Object) in.readValue((Object.class.getClassLoader())));
        this.dateOnSaleFromGmt = ((Object) in.readValue((Object.class.getClassLoader())));
        this.dateOnSaleTo = ((Object) in.readValue((Object.class.getClassLoader())));
        this.dateOnSaleToGmt = ((Object) in.readValue((Object.class.getClassLoader())));
        this.priceHtml = ((String) in.readValue((String.class.getClassLoader())));
        this.onSale = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
        this.purchasable = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
        this.totalSales = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.virtual = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
        this.downloadable = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
        in.readList(this.downloads, (java.lang.Object.class.getClassLoader()));
        this.downloadLimit = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.downloadExpiry = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.externalUrl = ((String) in.readValue((String.class.getClassLoader())));
        this.buttonText = ((String) in.readValue((String.class.getClassLoader())));
        this.taxStatus = ((String) in.readValue((String.class.getClassLoader())));
        this.taxClass = ((String) in.readValue((String.class.getClassLoader())));
        this.manageStock = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
        this.stockQuantity = ((Object) in.readValue((Object.class.getClassLoader())));
        this.stockStatus = ((String) in.readValue((String.class.getClassLoader())));
        this.backorders = ((String) in.readValue((String.class.getClassLoader())));
        this.backordersAllowed = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
        this.backordered = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
        this.soldIndividually = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
        this.weight = ((String) in.readValue((String.class.getClassLoader())));
        this.dimensions = ((Dimensions) in.readValue((Dimensions.class.getClassLoader())));
        this.shippingRequired = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
        this.shippingTaxable = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
        this.shippingClass = ((String) in.readValue((String.class.getClassLoader())));
        this.shippingClassId = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.reviewsAllowed = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
        this.averageRating = ((String) in.readValue((String.class.getClassLoader())));
        this.ratingCount = ((Integer) in.readValue((Integer.class.getClassLoader())));
        in.readList(this.relatedIds, (java.lang.Integer.class.getClassLoader()));
        in.readList(this.upsellIds, (java.lang.Object.class.getClassLoader()));
        in.readList(this.crossSellIds, (java.lang.Object.class.getClassLoader()));
        this.parentId = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.purchaseNote = ((String) in.readValue((String.class.getClassLoader())));
        in.readList(this.categories, (com.example.lafiores.model.product.Category.class.getClassLoader()));
        in.readList(this.tags, (java.lang.Object.class.getClassLoader()));
        this.lang = ((String) in.readValue((String.class.getClassLoader())));
        this.translations = ((Translations) in.readValue((Translations.class.getClassLoader())));
        in.readList(this.images, (com.example.lafiores.model.product.Image.class.getClassLoader()));
        in.readList(this.attributes, (com.example.lafiores.model.product.Attribute.class.getClassLoader()));
        in.readList(this.defaultAttributes, (com.example.lafiores.model.product.DefaultAttribute.class.getClassLoader()));
        in.readList(this.variations, (java.lang.Object.class.getClassLoader()));
        in.readList(this.groupedProducts, (java.lang.Object.class.getClassLoader()));
        this.menuOrder = ((Integer) in.readValue((Integer.class.getClassLoader())));
        in.readList(this.metaData, (java.lang.Object.class.getClassLoader()));
        this.links = ((Links) in.readValue((Links.class.getClassLoader())));
    }

    public Product() {
    }

    @Bindable
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
        notifyPropertyChanged(BR.id);
    }

    @Bindable
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        notifyPropertyChanged(BR.name);
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getPermalink() {
        return permalink;
    }

    public void setPermalink(String permalink) {
        this.permalink = permalink;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getDateCreatedGmt() {
        return dateCreatedGmt;
    }

    public void setDateCreatedGmt(String dateCreatedGmt) {
        this.dateCreatedGmt = dateCreatedGmt;
    }

    public String getDateModified() {
        return dateModified;
    }

    public void setDateModified(String dateModified) {
        this.dateModified = dateModified;
    }

    public String getDateModifiedGmt() {
        return dateModifiedGmt;
    }

    public void setDateModifiedGmt(String dateModifiedGmt) {
        this.dateModifiedGmt = dateModifiedGmt;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Boolean getFeatured() {
        return featured;
    }

    public void setFeatured(Boolean featured) {
        this.featured = featured;
    }

    public String getCatalogVisibility() {
        return catalogVisibility;
    }

    public void setCatalogVisibility(String catalogVisibility) {
        this.catalogVisibility = catalogVisibility;
    }

    @Bindable
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
        notifyPropertyChanged(BR.description);
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    @Bindable
    public String getPrice() {
        return price + " ₴";
    }

    public void setPrice(String price) {
        this.price = price;
        notifyPropertyChanged(BR.price);
    }

    public String getRegularPrice() {
        return regularPrice;
    }

    public void setRegularPrice(String regularPrice) {
        this.regularPrice = regularPrice;
    }

    @Bindable
    public String getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(String salePrice) {
        this.salePrice = salePrice;
        notifyPropertyChanged(BR.salePrice);
    }

    public Object getDateOnSaleFrom() {
        return dateOnSaleFrom;
    }

    public void setDateOnSaleFrom(Object dateOnSaleFrom) {
        this.dateOnSaleFrom = dateOnSaleFrom;
    }

    public Object getDateOnSaleFromGmt() {
        return dateOnSaleFromGmt;
    }

    public void setDateOnSaleFromGmt(Object dateOnSaleFromGmt) {
        this.dateOnSaleFromGmt = dateOnSaleFromGmt;
    }

    public Object getDateOnSaleTo() {
        return dateOnSaleTo;
    }

    public void setDateOnSaleTo(Object dateOnSaleTo) {
        this.dateOnSaleTo = dateOnSaleTo;
    }

    public Object getDateOnSaleToGmt() {
        return dateOnSaleToGmt;
    }

    public void setDateOnSaleToGmt(Object dateOnSaleToGmt) {
        this.dateOnSaleToGmt = dateOnSaleToGmt;
    }

    public String getPriceHtml() {
        return priceHtml;
    }

    public void setPriceHtml(String priceHtml) {
        this.priceHtml = priceHtml;
    }

    public Boolean getOnSale() {
        return onSale;
    }

    public void setOnSale(Boolean onSale) {
        this.onSale = onSale;
    }

    public Boolean getPurchasable() {
        return purchasable;
    }

    public void setPurchasable(Boolean purchasable) {
        this.purchasable = purchasable;
    }

    public Integer getTotalSales() {
        return totalSales;
    }

    public void setTotalSales(Integer totalSales) {
        this.totalSales = totalSales;
    }

    public Boolean getVirtual() {
        return virtual;
    }

    public void setVirtual(Boolean virtual) {
        this.virtual = virtual;
    }

    public Boolean getDownloadable() {
        return downloadable;
    }

    public void setDownloadable(Boolean downloadable) {
        this.downloadable = downloadable;
    }

    public List<Object> getDownloads() {
        return downloads;
    }

    public void setDownloads(List<Object> downloads) {
        this.downloads = downloads;
    }

    public Integer getDownloadLimit() {
        return downloadLimit;
    }

    public void setDownloadLimit(Integer downloadLimit) {
        this.downloadLimit = downloadLimit;
    }

    public Integer getDownloadExpiry() {
        return downloadExpiry;
    }

    public void setDownloadExpiry(Integer downloadExpiry) {
        this.downloadExpiry = downloadExpiry;
    }

    public String getExternalUrl() {
        return externalUrl;
    }

    public void setExternalUrl(String externalUrl) {
        this.externalUrl = externalUrl;
    }

    public String getButtonText() {
        return buttonText;
    }

    public void setButtonText(String buttonText) {
        this.buttonText = buttonText;
    }

    public String getTaxStatus() {
        return taxStatus;
    }

    public void setTaxStatus(String taxStatus) {
        this.taxStatus = taxStatus;
    }

    public String getTaxClass() {
        return taxClass;
    }

    public void setTaxClass(String taxClass) {
        this.taxClass = taxClass;
    }

    public Boolean getManageStock() {
        return manageStock;
    }

    public void setManageStock(Boolean manageStock) {
        this.manageStock = manageStock;
    }

    public Object getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(Object stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    public String getStockStatus() {
        return stockStatus;
    }

    public void setStockStatus(String stockStatus) {
        this.stockStatus = stockStatus;
    }

    public String getBackorders() {
        return backorders;
    }

    public void setBackorders(String backorders) {
        this.backorders = backorders;
    }

    public Boolean getBackordersAllowed() {
        return backordersAllowed;
    }

    public void setBackordersAllowed(Boolean backordersAllowed) {
        this.backordersAllowed = backordersAllowed;
    }

    public Boolean getBackordered() {
        return backordered;
    }

    public void setBackordered(Boolean backordered) {
        this.backordered = backordered;
    }

    public Boolean getSoldIndividually() {
        return soldIndividually;
    }

    public void setSoldIndividually(Boolean soldIndividually) {
        this.soldIndividually = soldIndividually;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public Dimensions getDimensions() {
        return dimensions;
    }

    public void setDimensions(Dimensions dimensions) {
        this.dimensions = dimensions;
    }

    public Boolean getShippingRequired() {
        return shippingRequired;
    }

    public void setShippingRequired(Boolean shippingRequired) {
        this.shippingRequired = shippingRequired;
    }

    public Boolean getShippingTaxable() {
        return shippingTaxable;
    }

    public void setShippingTaxable(Boolean shippingTaxable) {
        this.shippingTaxable = shippingTaxable;
    }

    public String getShippingClass() {
        return shippingClass;
    }

    public void setShippingClass(String shippingClass) {
        this.shippingClass = shippingClass;
    }

    public Integer getShippingClassId() {
        return shippingClassId;
    }

    public void setShippingClassId(Integer shippingClassId) {
        this.shippingClassId = shippingClassId;
    }

    public Boolean getReviewsAllowed() {
        return reviewsAllowed;
    }

    public void setReviewsAllowed(Boolean reviewsAllowed) {
        this.reviewsAllowed = reviewsAllowed;
    }

    public String getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(String averageRating) {
        this.averageRating = averageRating;
    }

    public Integer getRatingCount() {
        return ratingCount;
    }

    public void setRatingCount(Integer ratingCount) {
        this.ratingCount = ratingCount;
    }

    public List<Integer> getRelatedIds() {
        return relatedIds;
    }

    public void setRelatedIds(List<Integer> relatedIds) {
        this.relatedIds = relatedIds;
    }

    public List<Object> getUpsellIds() {
        return upsellIds;
    }

    public void setUpsellIds(List<Object> upsellIds) {
        this.upsellIds = upsellIds;
    }

    public List<Object> getCrossSellIds() {
        return crossSellIds;
    }

    public void setCrossSellIds(List<Object> crossSellIds) {
        this.crossSellIds = crossSellIds;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getPurchaseNote() {
        return purchaseNote;
    }

    public void setPurchaseNote(String purchaseNote) {
        this.purchaseNote = purchaseNote;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public List<Object> getTags() {
        return tags;
    }

    public void setTags(List<Object> tags) {
        this.tags = tags;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public Translations getTranslations() {
        return translations;
    }

    public void setTranslations(Translations translations) {
        this.translations = translations;
    }

    @Bindable
    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
        notifyPropertyChanged(BR.images);
    }

    public List<Attribute> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<Attribute> attributes) {
        this.attributes = attributes;
    }

    public List<DefaultAttribute> getDefaultAttributes() {
        return defaultAttributes;
    }

    public void setDefaultAttributes(List<DefaultAttribute> defaultAttributes) {
        this.defaultAttributes = defaultAttributes;
    }

    public List<Object> getVariations() {
        return variations;
    }

    public void setVariations(List<Object> variations) {
        this.variations = variations;
    }

    public List<Object> getGroupedProducts() {
        return groupedProducts;
    }

    public void setGroupedProducts(List<Object> groupedProducts) {
        this.groupedProducts = groupedProducts;
    }

    public Integer getMenuOrder() {
        return menuOrder;
    }

    public void setMenuOrder(Integer menuOrder) {
        this.menuOrder = menuOrder;
    }

    public List<Object> getMetaData() {
        return metaData;
    }

    public void setMetaData(List<Object> metaData) {
        this.metaData = metaData;
    }

    public Links getLinks() {
        return links;
    }

    public void setLinks(Links links) {
        this.links = links;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeValue(name);
        dest.writeValue(slug);
        dest.writeValue(permalink);
        dest.writeValue(dateCreated);
        dest.writeValue(dateCreatedGmt);
        dest.writeValue(dateModified);
        dest.writeValue(dateModifiedGmt);
        dest.writeValue(type);
        dest.writeValue(status);
        dest.writeValue(featured);
        dest.writeValue(catalogVisibility);
        dest.writeValue(description);
        dest.writeValue(shortDescription);
        dest.writeValue(sku);
        dest.writeValue(price);
        dest.writeValue(regularPrice);
        dest.writeValue(salePrice);
        dest.writeValue(dateOnSaleFrom);
        dest.writeValue(dateOnSaleFromGmt);
        dest.writeValue(dateOnSaleTo);
        dest.writeValue(dateOnSaleToGmt);
        dest.writeValue(priceHtml);
        dest.writeValue(onSale);
        dest.writeValue(purchasable);
        dest.writeValue(totalSales);
        dest.writeValue(virtual);
        dest.writeValue(downloadable);
        dest.writeList(downloads);
        dest.writeValue(downloadLimit);
        dest.writeValue(downloadExpiry);
        dest.writeValue(externalUrl);
        dest.writeValue(buttonText);
        dest.writeValue(taxStatus);
        dest.writeValue(taxClass);
        dest.writeValue(manageStock);
        dest.writeValue(stockQuantity);
        dest.writeValue(stockStatus);
        dest.writeValue(backorders);
        dest.writeValue(backordersAllowed);
        dest.writeValue(backordered);
        dest.writeValue(soldIndividually);
        dest.writeValue(weight);
        dest.writeValue(dimensions);
        dest.writeValue(shippingRequired);
        dest.writeValue(shippingTaxable);
        dest.writeValue(shippingClass);
        dest.writeValue(shippingClassId);
        dest.writeValue(reviewsAllowed);
        dest.writeValue(averageRating);
        dest.writeValue(ratingCount);
        dest.writeList(relatedIds);
        dest.writeList(upsellIds);
        dest.writeList(crossSellIds);
        dest.writeValue(parentId);
        dest.writeValue(purchaseNote);
        dest.writeList(categories);
        dest.writeList(tags);
        dest.writeValue(lang);
        dest.writeValue(translations);
        dest.writeList(images);
        dest.writeList(attributes);
        dest.writeList(defaultAttributes);
        dest.writeList(variations);
        dest.writeList(groupedProducts);
        dest.writeValue(menuOrder);
        dest.writeList(metaData);
        dest.writeValue(links);
    }

    public int describeContents() {
        return 0;
    }

    public static final DiffUtil.ItemCallback<Product> CALLBACK =
            new DiffUtil.ItemCallback<Product>() {
                @Override
                public boolean areItemsTheSame(@NonNull Product oldItem, @NonNull Product newItem) {
                    return oldItem.id == newItem.id;
                }

                @Override
                public boolean areContentsTheSame(@NonNull Product oldItem, @NonNull Product newItem) {
                    return true;
                }
            };

}