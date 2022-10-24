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
	ctx.JSON(http.StatusOK, service.GetVaccineById(int32(animalId)))

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

	service.AddVaccination(newVazination, int32(animalId))

	ctx.IndentedJSON(http.StatusCreated, newVazination)
}
