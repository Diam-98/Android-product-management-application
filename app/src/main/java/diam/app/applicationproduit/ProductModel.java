package diam.app.applicationproduit;

public class ProductModel {

    private int idProduit;
    private String nomProduit;
    private String categorieProduit;
    private int prixProduit;
    private String descriptionProduit;

    public ProductModel() {
    }

    public ProductModel(String nomProduit, String categorieProduit, int prixProduit, String descriptionProduit) {
        this.nomProduit = nomProduit;
        this.categorieProduit = categorieProduit;
        this.prixProduit = prixProduit;
        this.descriptionProduit = descriptionProduit;
    }

    public ProductModel(int idProduit, String nomProduit, String categorieProduit, int prixProduit, String descriptionProduit) {
        this.idProduit = idProduit;
        this.nomProduit = nomProduit;
        this.categorieProduit = categorieProduit;
        this.prixProduit = prixProduit;
        this.descriptionProduit = descriptionProduit;
    }

    public int getIdProduit() {
        return idProduit;
    }

    public void setIdProduit(int idProduit) {
        this.idProduit = idProduit;
    }

    public String getNomProduit() {
        return nomProduit;
    }

    public void setNomProduit(String nomProduit) {
        this.nomProduit = nomProduit;
    }

    public String getCategorieProduit() {
        return categorieProduit;
    }

    public void setCategorieProduit(String categorieProduit) {
        this.categorieProduit = categorieProduit;
    }

    public int getPrixProduit() {
        return prixProduit;
    }

    public void setPrixProduit(int prixProduit) {
        this.prixProduit = prixProduit;
    }

    public String getDescriptionProduit() {
        return descriptionProduit;
    }

    public void setDescriptionProduit(String descriptionProduit) {
        this.descriptionProduit = descriptionProduit;
    }
}
