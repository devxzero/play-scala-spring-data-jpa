import javax.persistence.{Persistence, EntityManagerFactory}

import org.springframework.context.annotation.AnnotationConfigApplicationContext
import org.springframework.orm.hibernate3.HibernateExceptionTranslator
import org.springframework.orm.jpa.JpaTransactionManager
import play.api.{Application, GlobalSettings}
import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.context.annotation.Bean

/**
 * Application wide behaviour. We establish a Spring application context for the dependency injection system and
 * configure Spring Data.
 */
object Global extends GlobalSettings {

  /**
   * Declare the application context to be used. AnnotationConfigApplicationContext cannot be refreshed so we can
   * only do this once. We tell the context what packages to scan and then to refresh itself.
   */
  val ctx = new AnnotationConfigApplicationContext
//  ctx.scan("controllers", "services")
//  ctx.refresh()

  /**
   * Sync the context lifecycle with Play's.
   */
  override def onStart(app: Application) {
    super.onStart(app)

    // AnnotationConfigApplicationContext can only be refreshed once, but we do it here even though this method
    // can be called multiple times. The reason for doing during startup is so that the Play configuration is
    // entirely available to this application context.
    ctx.register(classOf[SpringDataJpaConfiguration])
    ctx.scan("controllers", "models")
    ctx.refresh


    ctx.start()
  }

  /**
   * Sync the context lifecycle with Play's.
   * @param app
   */
  override def onStop(app: Application) {
    // This will call any destruction lifecycle methods and then release the beans e.g. @PreDestroy
    ctx.close

    super.onStop(app)
  }

  /**
   * Controllers must be resolved through the application context. There is a special method of GlobalSettings
   * that we can override to resolve a given controller. This resolution is required by the Play router.
   * @param controllerClass
   * @tparam A
   * @return
   */
  override def getControllerInstance[A](controllerClass: Class[A]): A = ctx.getBean(controllerClass)
}
@Configuration
@EnableJpaRepositories(Array("models"))
class SpringDataJpaConfiguration {
  @Bean def entityManagerFactory: EntityManagerFactory = {
    return Persistence.createEntityManagerFactory("default")
  }

  @Bean def hibernateExceptionTranslator: HibernateExceptionTranslator = {
    return new HibernateExceptionTranslator
  }

  @Bean def transactionManager: JpaTransactionManager = {
    return new JpaTransactionManager
  }
}
