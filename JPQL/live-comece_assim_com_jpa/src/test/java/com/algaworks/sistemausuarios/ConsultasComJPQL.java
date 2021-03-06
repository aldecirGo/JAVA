package com.algaworks.sistemausuarios;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import com.algaworks.sistemausuarios.dto.UsuarioDTO;
import com.algaworks.sistemausuarios.model.Dominio;
import com.algaworks.sistemausuarios.model.Usuario;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;

public class ConsultasComJPQL {

	public static void main(String[] args) {
		
		EntityManagerFactory entityManagerFactory = 
					Persistence.createEntityManagerFactory("Clientes-PU");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		
		//primeirasConsultas(entityManager);
		//segundoRetorno(entityManager);
		//fazendoProjecoes(entityManager);
		//passandoParametros(entityManager);
		//fezendoJoin(entityManager);
		//CarregandoComJoinFetch(entityManager);
		//filtrandoRegistros(entityManager);
		//utilizandoOPeradorLogico(entityManager);
		//ordenandoResultados(entityManager);
		//paginandoResultados(entityManager);
		
		entityManager.close();  	
		entityManagerFactory.close();
		System.exit(0);
	}
	public static void paginandoResultados(EntityManager entityManager) {
		System.out.println("paginandoResultados \n-----------------");
		String jpql = "select  u from Usuario u";		
		
		
		TypedQuery<Usuario> typedQuery = entityManager.createQuery(jpql,Usuario.class);
		int qtde = typedQuery.getResultList().size();
		
		int itensPg = 4;
		typedQuery.setMaxResults(itensPg);
		
		for(int i = 0 ; i < qtde; i+=itensPg) {
			System.out.println("pagina : " + ((i+itensPg)/itensPg));
			typedQuery.setFirstResult(i);
			List<Usuario> lista = typedQuery.getResultList();			
			lista.forEach(u -> 		System.out.println( u.getId() + " ->  "  + u.getNome())		);
		}
	}
	public static void ordenandoResultados(EntityManager entityManager) {
		System.out.println("ordenandoResultados \n-----------------");
		String jpql = "select  u from Usuario u order by  nome desc ";		
		TypedQuery<Usuario> typedQuery = entityManager.createQuery(jpql,Usuario.class);;
		List<Usuario> lista = typedQuery.getResultList();			
		lista.forEach(u -> 		System.out.println( u.getId() + " ->  "  + u.getNome())		);
		
	}
	public static void utilizandoOPeradorLogico(EntityManager entityManager) {
		
		System.out.println("utilizandoOPeradorLogico \n-----------------");
		System.out.println("\n > AND OR 'IS NULL' \n------");
		String jpql = "select  u from Usuario u where ( u.ultimoAcesso > :ontem and u.ultimoAcesso < :hoje )"
				    + " OR u.ultimoAcesso IS NULL ";		
		TypedQuery<Usuario> typedQuery = entityManager.createQuery(jpql,Usuario.class);
										typedQuery.setParameter("ontem",        LocalDateTime.now().minusDays(1));
										typedQuery.setParameter("hoje", LocalDateTime.now());
		List<Usuario> lista = typedQuery.getResultList();			
		lista.forEach(u -> 		System.out.println( u.getId() + " ->  "  + u.getNome())		);
	
		
		System.out.println("\n > In' \n------");
		String jpqlIn = "select  u from Usuario u where id in ( :idIn )";
		TypedQuery<Usuario> typedQueryIn = entityManager.createQuery(jpqlIn,Usuario.class);
		typedQueryIn.setParameter("idIn", Arrays.asList(1,2,3) );
		List<Usuario> listaIn = typedQueryIn.getResultList();			
		listaIn.forEach(u -> 		System.out.println( u.getId() + " ->  "  + u.getNome())		);
		
	}
	public static void filtrandoRegistros(EntityManager entityManager) {
		
		System.out.println("filtrandoRegistros \n-----------------");
		
		//Like, between, >, =, isNull, isEmpty, <> >=, ...
		System.out.println("\n like \n------");
		String jpql = "select  u from Usuario u where u.nome like concat(:nomeUsuario,'%') ";		
		TypedQuery<Usuario> typedQuery = entityManager.createQuery(jpql,Usuario.class);
										typedQuery.setParameter("nomeUsuario", "Cal");
		List<Usuario> lista = typedQuery.getResultList();			
		lista.forEach(u -> 		System.out.println( u.getId() + " ->  "  + u.getNome())		);
		
		System.out.println("\n between \n---------");
		String jpqlBetw = "select  u from Usuario u where u.ultimoAcesso between :to and :from ";		
		TypedQuery<Usuario> typedQueryBtw = entityManager.createQuery(jpqlBetw,Usuario.class);
		typedQueryBtw.setParameter("to", LocalDateTime.now().minusDays(1) );
		typedQueryBtw.setParameter("from", LocalDateTime.now());
		List<Usuario> listaBtw = typedQueryBtw.getResultList();			
		listaBtw.forEach(u -> 		System.out.println( u.getId() + " ->  "  + u.getNome())		);
		
		
		
	}
	public static void CarregandoComJoinFetch(EntityManager entityManager) {
		System.out.println("CarregandoComJoinFetch \n-----------------");
		
		String jpql = "select  u from Usuario u join fetch u.configuracao join fetch u.dominio d";
		TypedQuery<Usuario> typedQuery = entityManager.createQuery(jpql,Usuario.class);
		List<Usuario> lista = typedQuery.getResultList();	
		
		lista.forEach(u -> 
	
			System.out.println( u.getId() + " ->  "  + u.getNome())
				
		);	
	}
	public static void fazendoLeftJoin(EntityManager entityManager) {
		System.out.println("fezendoLeftJoin \n-----------------");
		
		String jpql = "select  u, c  from Usuario u  left join u.configuracao c ";
		TypedQuery<Object[]> typedQuery = entityManager.createQuery(jpql, Object[].class);
		List<Object[]> lista = typedQuery.getResultList();	
		
		lista.forEach(arr -> 
			System.out.println(arr.length)
			//System.out.println( ((Usuario) arr[0]).getNome() + " ->  "  +  (arr[1]==null ? "N?o" : ((Configuracao) arr[1]).getId()));
			//System.out.println( ((Usuario) arr[0]).getNome() + " ->  "  +  (arr[1]==null ? "N?o" : arr[1]))
				
		);	
	}
	public static void fezendoJoin(EntityManager entityManager) {
		System.out.println("fezendoJoin \n-----------------");
		String jpql = "select u from Usuario u join u.dominio d where d.id = 1 ";
		TypedQuery<Usuario> typedQuery = entityManager.createQuery(jpql, Usuario.class);
		List<Usuario> lista = typedQuery.getResultList();		
		lista.forEach(u -> System.out.println( u.getId() + " ->  "  +  u.getNome()));	
	}
	public static void passandoParametros(EntityManager entityManager) {
		String jpql = "select u from Usuario u where u.login = :idUsuario ";
		TypedQuery<Usuario> typedQuery = entityManager
										.createQuery(jpql, Usuario.class)
										.setParameter("idUsuario", "eli");
		Usuario usuario = typedQuery.getSingleResult();		
		System.out.println( usuario.getId() + ",  "  +  usuario.getNome());		
		
		
	}
	public static void fazendoProjecoes(EntityManager entityManager) {
		
		String jpql = "select u.id, u.login, u.nome from Usuario u";
		TypedQuery<Object[]> typedQuery = entityManager.createQuery(jpql, Object[].class);
		List<Object[]> listaArr = typedQuery.getResultList();		
		listaArr.forEach(arr -> System.out.println( String.format("%s,%s,%s", arr))	);   
		
		
		String jpqlDto = " select new com.algaworks.sistemausuarios.dto.UsuarioDTO(id, login, nome)" +
		                 " from Usuario";
		TypedQuery<UsuarioDTO> typedQueryDto = entityManager.createQuery(jpqlDto, UsuarioDTO.class);
		List<UsuarioDTO> listDto = typedQueryDto.getResultList();		
		listDto.forEach(dto -> System.out.println("DTO : " + dto.getId() + " , " +  dto.getNome()	));   
		
		
		
	}
	public static void segundoRetorno(EntityManager entityManager) {
		String jpql = "select u.dominio  from Usuario u where u.id = 1";
		TypedQuery<Dominio> typedQuery = entityManager.createQuery(jpql, Dominio.class);
		Dominio dominio = typedQuery.getSingleResult();		
		System.out.println( dominio.getId() + ",  "  +  dominio.getNome());		
		
		String jpqlNom = "select u.nome from Usuario u";
		TypedQuery<String> typedQueryNome = entityManager.createQuery(jpqlNom, String.class);
		List<String> listaNome = typedQueryNome.getResultList();		
		listaNome.forEach(nome -> System.out.println( nome)		);
				
	}
	public static void primeirasConsultas(EntityManager entityManager) {
		
		String jpql = "select u from Usuario u";
		TypedQuery<Usuario> typedQuery = entityManager.createQuery(jpql, Usuario.class);
		List<Usuario> lista = typedQuery.getResultList();		
		lista.forEach(u -> 
			System.out.println( u.getId() + ",  "  +  u.getNome())		
		);
		
		String jpqlSingle = "select u from Usuario u where u.id = 1";
		TypedQuery<Usuario> typedQuerySingle = entityManager.createQuery(jpqlSingle, Usuario.class);
		Usuario usuario = typedQuerySingle.getSingleResult();
		
		System.out.println( usuario.getId() + ",  "  +  usuario.getNome());	
		
		jpql = "select u from Usuario u where u.id = 2";
		Query query = entityManager.createQuery(jpql);
		Usuario usuario2 = (Usuario) query.getSingleResult();
		
		System.out.println( usuario2.getId() + ",  "  +  usuario2.getNome());	
		
		
		jpql = "select u from Usuario u where lower(nome) like '%g%'";
		typedQuery = entityManager.createQuery(jpql, Usuario.class);
		lista = typedQuery.getResultList();
		
		lista.forEach(u -> 
			System.out.println( u.getId() + ",  "  +  u.getNome())		
		);
		
	}
}
