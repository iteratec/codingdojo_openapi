package controller

import (
	"net/http"

	"github.com/gin-gonic/gin"

	"vet-health-golang/model"
)

// func GetPong(ctx *gin.Context) {
// 	ctx.JSON(http.StatusOK, gin.H{
// 		"message": ctx.Param("animalid"),
// 	})
// }

// GetVakzin godoc
// @Summary      Show an vakzination
// @Description  get vakzination by AnimalID
// @Tags         vakzin
// @Accept       json
// @Produce      json
// @Param        id   path      int  true  "Animal ID"
// @Success      200  {object}  model.Vakzin
// @Router       /vakzin/{id} [get]
func GetVazinById(ctx *gin.Context) {
	ctx.JSON(http.StatusOK, gin.H{
		"message": ctx.Param("animalid"),
	})
}

// AddVazination godoc
// @Summary      Adds an vakzination
// @Description  adds vakzination by AnimalID
// @Tags         vakzin
// @Accept       json
// @Produce      json
// @Param        id   path      int  true  "Animal ID"
// @Param 		 reqest body model.Vakzin true "query params"
// @Success      200  {object}  model.Vakzin
// @Router       /vakzin/{id} [post]
func AddVazination(ctx *gin.Context) {
	var newVazination model.Vakzin

	if err := ctx.BindJSON(&newVazination); err != nil {
		return
	}

	ctx.IndentedJSON(http.StatusCreated, newVazination)
}

// service.FindPong()
