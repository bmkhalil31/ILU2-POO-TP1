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
			boolean continuer = true;
			int i;
			for (i = 0; (i < nbEtals && continuer); i++) {
				continuer = etals[i].isEtalOccupe();
			}

			return i;

		}

		public Etal[] trouverEtals(String produit) {
			int nb = nbEtalsProduit(produit);
			Etal[] etalsProduit = new Etal[nb];
			int j = 0;
			for (int i = 0; i < nbEtals; i++) {
				if (etals[i].contientProduit(produit)) {
					etalsProduit[j] = etals[i];
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
		
		public void afficherMarcher() {
			int nbEtalLibre=0;
			
			for (int i = 0; i < nbEtals; i++) {
				
				if (etals[i].isEtalOccupe()) {
					etals[i].afficherEtal();
				}else {
					nbEtalLibre++;
				}
				
			}
			if (nbEtalLibre>0) {
				System.out.println("Il reste "+nbEtalLibre+" etals non utilises dans le marche");
			}
		}

	}
	
//	public void rechercherVendeurProduit(String produit) {
//		//to do
//	 Etal[] etalsProduit=marche.trouverEtals(produit);
//	 switch (etalsProduit.length) {
//	case 0:
//		System.out.println("Il n' y a pas de ve");
//		break;
//
//	default:
//		break;
//	}
//		System.out.println("Il n'y a pas de vendeur qui propose des "+produit+" au marche");
//	} else if() {
//
//	}
//		
//	}

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