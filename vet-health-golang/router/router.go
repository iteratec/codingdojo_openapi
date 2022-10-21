package router

import (
	"vet-health-golang/controller"

	"github.com/gin-gonic/gin"
)

func InitRouter() {
	router := gin.Default()

	router.Use(gin.Logger())
	router.Use(gin.Recovery())

	router.GET("/ping", controller.GetPong)

	router.Run()
}
