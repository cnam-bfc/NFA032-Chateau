package net.cnam.chateau.item;

public class Item {
    private String name;
    private String description;

    /**
     * Constructeur
     *
     * @param name        Nom de l'objet
     * @param description Description de l'objet
     */
    public Item(String name, String description) {
        this.name = name;
        this.description = description;
    }

    /**
     * Getter permettant de récupérer le nom de l'objet.
     *
     * @return Le nom de l'objet (String)
     */
    public String getName() {
        return name;
    }

    /**
     * Setter permettant de définir le nom de l'objet.
     *
     * @param name Nom de l'objet à attribuer (String)
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Getter permettant de récupérer la description de l'objet.
     *
     * @return La description de l'objet (String)
     */
    public String getDescription() {
        return description;
    }

    /**
     * Setter permettant de définir la description de l'objet.
     *
     * @param description La description de l'objet à attribuer (String)
     */
    public void setDescription(String description) {
        this.description = description;
    }
}
