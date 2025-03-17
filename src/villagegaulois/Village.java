package villagegaulois;

import personnages.Chef;
import personnages.Gaulois;

public class Village {
	private String nom;
	private Chef chef;
	private Gaulois[] villageois;
	private int nbVillageois = 0;
	private Marche marche;

	public Village(String nom, int nbVillageoisMaximum,int nbEtals) {
		marche=new Marche(nbEtals);
		this.nom = nom;
		villageois = new Gaulois[nbVillageoisMaximum];
	}

	public class Marche {

		private int nbEtals;
		private Etal[] etals;

		public Marche(int nbEtals) {
			this.nbEtals = nbEtals;
			etals = new Etal[nbEtals];
			for (int i = 0; i < nbEtals; i++) {
				etals[i] = new Etal();
			}
		}

		public void utiliserEtal(int indiceEtal, Gaulois vendeur, String produit, int nbProduit) {

			etals[indiceEtal].occuperEtal(vendeur, produit, nbProduit);
		}

		public int trouverEtalLibre() {
			int i=0;
			while(i < nbEtals && etals[i].isEtalOccupe()) {
				 i++;
			}
			System.out.println(i);
            if(i==nbEtals)
            	return -1;
			return i;

		}

		public Etal[] trouverEtals(String produit) {
			int nb = nbEtalsProduit(produit);
			Etal[] etalsProduit = new Etal[nb];
			int j = 0;
			for (int i = 0; i < nbEtals; i++) {
				if (etals[i].contientProduit(produit)) {
					etalsProduit[j] = etals[i];
					j++;
				}

			}
			return etalsProduit;

		}

		private int nbEtalsProduit(String produit) {
			int nb = 0;
			for (int i = 0; i < nbEtals; i++) {
				if (etals[i].contientProduit(produit)) {
					nb++;
				}

			}

			return nb;
		}

		public Etal trouverVendeur(Gaulois gaulois) {

			Etal etal = null;

			for (int i = 0; (i < nbEtals); i++) {
				if (etals[i].getVendeur().equals(gaulois)) {
					etal = etals[i];
					return etal;
				}

			}
			return etal;

		}
		
		public String afficherMarcher() {
		    int nbEtalLibre = 0;
		    StringBuilder infoMarche = new StringBuilder();

		    for (int i = 0; i < nbEtals; i++) {
		        if (etals[i].isEtalOccupe()) {
		            infoMarche.append(etals[i].afficherEtal());
		        } else {
		            nbEtalLibre++;
		        }
		    }

		    if (nbEtalLibre > 0) {
		        infoMarche.append("Il reste "+nbEtalLibre+" étals non utilisés dans le marché.");
		    }

		    return infoMarche.toString();
		}


	}
	
	public String rechercherVendeursProduit(String produit) {
	    Etal[] etalsProduit = marche.trouverEtals(produit);
	    
	    switch (etalsProduit.length) {
	        case 0:
	            return "Il n'y a pas de vendeur qui propose des " + produit + " au marché";
	        case 1:
	            return "Seul le vendeur " + etalsProduit[0].getVendeur().getNom() + " propose des " + produit + " au marché";
	        default:
	            StringBuilder listesVendeurs = new StringBuilder("Les vendeurs qui proposent des " + produit + " sont :\n");
	            for (int i = 0; i < etalsProduit.length; i++) {
	                listesVendeurs.append(etalsProduit[i].getVendeur().getNom()).append("\n");
	            }
	            return listesVendeurs.toString();
	    }
	}
	public String installerVendeur(Gaulois vendeur, String produit, int nbProduits) {
	    int numEtal = marche.trouverEtalLibre();
	    StringBuilder chaineRetournee = new StringBuilder();

	    chaineRetournee.append(vendeur.getNom())
	                   .append(" cherche un endroit pour vendre ")
	                   .append(nbProduits).append(" ").append(produit).append(".\n");

	    if (numEtal == -1) {
	        chaineRetournee.append("Aucun étal n'est libre.");
	    } else {
	        marche.utiliserEtal(numEtal, vendeur, produit, nbProduits);
	        numEtal++;
	        chaineRetournee.append("Le vendeur "+vendeur.getNom()+" vend des "+produit+" à l'étal n°"+numEtal+".\n");
	                       
	    }
	    
	    return chaineRetournee.toString();
	}
	
	public Etal rechercherEtal(Gaulois vendeur) {
		return marche.trouverVendeur(vendeur);
		
	}
	
	public String partirVendeur(Gaulois vendeur) {
		
		Etal etalQuitte=rechercherEtal(vendeur);
		return etalQuitte.libererEtal();
		
	}
	public String afficherMarche() {
		return marche.afficherMarcher();
	}


	public String getNom() {
		return nom;
	}

	public void setChef(Chef chef) {
		this.chef = chef;
	}

	public void ajouterHabitant(Gaulois gaulois) {
		if (nbVillageois < villageois.length) {
			villageois[nbVillageois] = gaulois;
			nbVillageois++;
		}
	}

	public Gaulois trouverHabitant(String nomGaulois) {
		if (nomGaulois.equals(chef.getNom())) {
			return chef;
		}
		for (int i = 0; i < nbVillageois; i++) {
			Gaulois gaulois = villageois[i];
			if (gaulois.getNom().equals(nomGaulois)) {
				return gaulois;
			}
		}
		return null;
	}

	public String afficherVillageois() {
		StringBuilder chaine = new StringBuilder();
		if (nbVillageois < 1) {
			chaine.append("Il n'y a encore aucun habitant au village du chef " + chef.getNom() + ".\n");
		} else {
			chaine.append("Au village du chef " + chef.getNom() + " vivent les légendaires gaulois :\n");
			for (int i = 0; i < nbVillageois; i++) {
				chaine.append("- " + villageois[i].getNom() + "\n");
			}
		}
		return chaine.toString();
	}
}