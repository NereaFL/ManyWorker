package ManyWorker.entity;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Patrocinio extends DomainEntity {

		@NotBlank
		private String banner;
		
		@NotBlank
		private String enlacePaginaObjetivo;

		// Constructor vacío
		public Patrocinio() {
			super();
		}
		
		// Getters y setters
		public String getBanner() {
			return banner;
		}

		public void setBanner(String banner) {
			this.banner = banner;
		}

		public String getEnlacePaginaObjetivo() {
			return enlacePaginaObjetivo;
		}

		public void setEnlacePaginaObjetivo(String enlacePaginaObjetivo) {
			this.enlacePaginaObjetivo = enlacePaginaObjetivo;
		}
			
}
