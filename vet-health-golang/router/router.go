package router

import (
	"vet-health-golang/controller"

	"github.com/gin-gonic/gin"

	_ "vet-health-golang/docs"
	// swaggerFiles "github.com/swaggo/files"
	// ginSwagger "github.com/swaggo/gin-swagger"
)

func InitRouter() {

	router := gin.Default()

	router.Use(gin.Logger())
	router.Use(gin.Recovery())

	v1 := router.Group("/v1")
	{
		vaccine := v1.Group("/vaccination")
		{
			vaccine.GET(":animalType", controller.GetVaccineById)
			vaccine.POST(":animalType", controller.AddVaccination)
		}
	}

	router.Run()
}
