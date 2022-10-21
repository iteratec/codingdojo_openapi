package router

import (
	"vet-health-golang/controller"

	"github.com/gin-gonic/gin"

	swaggerFiles "github.com/swaggo/files"
	ginSwagger "github.com/swaggo/gin-swagger"
)

func InitRouter() {
	router := gin.Default()

	router.Use(gin.Logger())
	router.Use(gin.Recovery())

	v1 := router.Group("/api/v1/health")
	{
		vakzin := v1.Group("/vakzin")
		{
			vakzin.GET(":id", controller.GetVazinById)
			vakzin.POST(":id", controller.AddVazination)
		}
	}

	//router.GET("/ping/:animalid", controller.GetPong)
	router.GET("/swagger/*any", ginSwagger.WrapHandler(swaggerFiles.Handler))
	router.Run()
}
