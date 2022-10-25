package controller

import (
	"net/http"
	"strconv"

	"github.com/gin-gonic/gin"

	"vet-health-golang/model"
	"vet-health-golang/service"
)

func GetVaccineById(ctx *gin.Context) {

	animalId, err := strconv.Atoi(ctx.Param("id"))

	if err != nil {
		ctx.JSON(http.StatusBadRequest, gin.H{"error": "animalid must be a number"})
		return
	}

	ret := service.GetVaccineById(int32(animalId))

	if ret == nil {
		ctx.JSON(http.StatusNotFound, gin.H{"code": "PAGE_NOT_FOUND", "message": "Page not found"})
		return
	}

	ctx.JSON(http.StatusOK, *ret)

}

func AddVaccination(ctx *gin.Context) {

	animalId, err := strconv.Atoi(ctx.Param("id"))

	if err != nil {
		ctx.JSON(http.StatusBadRequest, gin.H{"error": "animalid must be a number"})
		return
	}

	var newVazination model.Vaccine

	if err := ctx.BindJSON(&newVazination); err != nil {
		ctx.AbortWithError(http.StatusBadRequest, err)
		return
	}

	serviceError := service.AddVaccination(newVazination, int32(animalId))

	if serviceError != nil {
		ctx.AbortWithError(http.StatusBadRequest, serviceError)
		return
	}

	ctx.IndentedJSON(http.StatusCreated, newVazination)
}
