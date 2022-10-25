package controller

import (
	"net/http"

	"github.com/gin-gonic/gin"

	"vet-health-golang/model"
	"vet-health-golang/service"
)

func GetVaccineById(ctx *gin.Context) {

	ret := service.GetVaccineByType(ctx.Param("animalType"))

	if ret == nil {
		ctx.JSON(404, gin.H{"code": "VACCINE_NOT_FOUND", "message": "Vaccine not found"})
		return
	}

	ctx.JSON(http.StatusOK, *ret)

}

func AddVaccination(ctx *gin.Context) {

	var newVazination model.Vaccine

	if err := ctx.BindJSON(&newVazination); err != nil {
		ctx.AbortWithError(http.StatusBadRequest, err)
		return
	}

	serviceError := service.AddVaccination(newVazination, ctx.Param("animalType"))

	if serviceError != nil {
		ctx.AbortWithError(406, serviceError)
		return
	}

	ctx.IndentedJSON(http.StatusCreated, newVazination)
}
