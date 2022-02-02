package es.gom.ApiFC;

import es.gom.ApiFC.config.PasswordConfig;
import es.gom.ApiFC.entities.Candidato;
import es.gom.ApiFC.entities.Etiqueta;
import es.gom.ApiFC.entities.Presencialidad;
import es.gom.ApiFC.entities.Usuario;
import es.gom.ApiFC.repositories.CandidatoRepository;
import es.gom.ApiFC.repositories.EtiquetaRepository;
import es.gom.ApiFC.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@SpringBootApplication
public class ApiFcApplication {

	public static void main(String[] args) {

		ApplicationContext context = SpringApplication.run(ApiFcApplication.class, args);

		//Repositorios
		CandidatoRepository candidatoRepository = context.getBean(CandidatoRepository.class);
		UsuarioRepository usuarioRepository = context.getBean(UsuarioRepository.class);
		EtiquetaRepository etiquetaRepository = context.getBean(EtiquetaRepository.class);


		BCryptPasswordEncoder passwordEncoder = PasswordConfig.passwordEncoder();

		//Instancias
		Usuario usuario1 = new Usuario(null, "ejemplo@ejemplo.com", "usuario1", passwordEncoder.encode("1234"));
		Usuario usuario2 = new Usuario(null, "ejemplo2@ejemplo.com", "usuario2", passwordEncoder.encode("1234"));

		Candidato candidato1 = new Candidato(null,"cand1", "Madrid",
				"España", "666666", "email@email.email", true, Presencialidad.MIXTO);
		Candidato candidato2 = new Candidato(null,"cand2", "Valencia",
				"España", "666677", "email2@email.email", true, Presencialidad.REMOTO);
		Candidato candidato3 = new Candidato(null,"cand3", "Sevilla",
				"España", "666688", "email3@email.email", false, Presencialidad.PRESENCIAL);
		Candidato candidato4 = new Candidato(null,"cand4", "Madrid",
				"España", "666699", "email4@email.email", true, Presencialidad.PRESENCIAL);
		Candidato candidato5 = new Candidato(null,"cand1 cand1", "Medellín",
				"Colombia", "666622", "email5@email.email", false, Presencialidad.REMOTO);
		Candidato candidato6 = new Candidato(null,"cand6", "Valencia",
				"España", "66336666", "email7@email.email", true, Presencialidad.MIXTO);
		Candidato candidato7 = new Candidato(null,"cand222", "Valencia",
				"España", "66126677", "email8@email.email", true, Presencialidad.PRESENCIAL);
		Candidato candidato8 = new Candidato(null,"cand3", "Sevilla",
				"España", "66623688", "email9@email.email", true, Presencialidad.REMOTO);
		Candidato candidato9 = new Candidato(null,"cand4", "Madrid",
				"España", "6663699", "email10@email.email", false, Presencialidad.PRESENCIAL);
		Candidato candidato10 = new Candidato(null,"cand1 cand1", "Medellín",
				"Colombia", "665622", "email11@email.email", false, Presencialidad.MIXTO);

		Etiqueta etiqueta1 = new Etiqueta(null, "Java", 1);
		Etiqueta etiqueta2 = new Etiqueta(null, "Spring", 2);
		Etiqueta etiqueta3 = new Etiqueta(null, "Html", 1);

		//Guardado y relaciones
		etiquetaRepository.save(etiqueta1);
		etiquetaRepository.save(etiqueta2);
		etiquetaRepository.save(etiqueta3);

		candidato1.getEtiquetas().add(etiqueta1);
		candidato2.getEtiquetas().add(etiqueta2);
		candidato3.getEtiquetas().add(etiqueta1);
		candidato3.getEtiquetas().add(etiqueta2);
		candidato4.getEtiquetas().add(etiqueta3);
		candidato4.getEtiquetas().add(etiqueta1);
		candidato5.getEtiquetas().add(etiqueta2);
		candidato5.getEtiquetas().add(etiqueta3);
		candidato5.getEtiquetas().add(etiqueta1);
		candidato6.getEtiquetas().add(etiqueta1);
		candidato6.getEtiquetas().add(etiqueta2);
		candidato7.getEtiquetas().add(etiqueta3);
		candidato8.getEtiquetas().add(etiqueta2);
		candidato8.getEtiquetas().add(etiqueta3);
		candidato9.getEtiquetas().add(etiqueta1);
		candidato9.getEtiquetas().add(etiqueta2);
		candidato10.getEtiquetas().add(etiqueta3);
		candidato10.getEtiquetas().add(etiqueta1);
		candidato10.getEtiquetas().add(etiqueta2);



		candidatoRepository.saveAll(List.of(candidato1, candidato2, candidato3, candidato4, candidato5, candidato6, candidato7,candidato8,candidato9,candidato10));
		/*candidatoRepository.save(candidato2);
		candidatoRepository.save(candidato3);
		candidatoRepository.save(candidato4);
		candidatoRepository.save(candidato5);
		candidatoRepository.save(candidato6);
		candidatoRepository.save(candidato7);
		candidatoRepository.save(candidato8);
		candidatoRepository.save(candidato9);
		candidatoRepository.save(candidato10);*/

		usuario1.getCandidatos().add(candidato1);
		usuario1.getCandidatos().add(candidato2);
		usuario2.getCandidatos().add(candidato3);
		usuario2.getCandidatos().add(candidato4);
		usuario2.getCandidatos().add(candidato5);
		usuario1.getCandidatos().add(candidato6);
		usuario1.getCandidatos().add(candidato7);
		usuario2.getCandidatos().add(candidato8);
		usuario2.getCandidatos().add(candidato9);
		usuario2.getCandidatos().add(candidato10);


		usuarioRepository.save(usuario1);
		usuarioRepository.save(usuario2);
		candidato1.setUsuario(usuario1);
		candidato2.setUsuario(usuario1);
		candidato3.setUsuario(usuario2);
		candidato4.setUsuario(usuario2);
		candidato5.setUsuario(usuario2);

		candidatoRepository.saveAll(List.of(candidato1, candidato2, candidato3, candidato4, candidato5));
		candidatoRepository.save(candidato2);
		candidatoRepository.save(candidato3);
		candidatoRepository.save(candidato4);
		candidatoRepository.save(candidato5);

	}

}
