package com.github.sursmobil

import com.github.sursmobil.http.AllRoutes
import com.github.sursmobil.service.recipes.RecipesServiceComponent
import com.github.sursmobil.util.{ActorSystemComponent, AkkaHttpServerComponent}

object Main
    extends App
    with AkkaHttpServerComponent.Default
    with ActorSystemComponent.Default
    with AllRoutes
    with RecipesServiceComponent.Dummy {

  httpServer.start()
}
