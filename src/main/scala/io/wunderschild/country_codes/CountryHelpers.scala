package io.wunderschild.country_codes

import scala.reflect._
import scala.reflect.runtime.universe._

object CountryHelpers {
  /** Takes a map and instantiate arbitrary case class.
   *
   * @param m arguments needed to instantiate a case class
   */
  implicit class RichMapToCaseClass(val m: Map[String, Any]) {
    def toCaseClass[T: TypeTag : ClassTag]: T = {
      val tpe = typeOf[T]
      val rm = runtimeMirror(getClass.getClassLoader)
      val classSymbol = tpe.typeSymbol.asClass
      val classMirror = rm.reflectClass(classSymbol)

      val constructor = tpe.decl(termNames.CONSTRUCTOR).asMethod
      val constructorMirror = classMirror.reflectConstructor(constructor)

      val constructorArgs = constructor.paramLists.flatten.map( (param: Symbol) => {
        val paramName = param.name.toString
        if(param.typeSignature <:< typeOf[Option[Any]])
          m.get(paramName)
        else
          m.getOrElse(paramName, throw new IllegalArgumentException("Map is missing required parameter named " + paramName))
      })

      constructorMirror(constructorArgs:_*).asInstanceOf[T]
    }
  }
}
