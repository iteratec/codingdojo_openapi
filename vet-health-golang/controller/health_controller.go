package controller

import (
	"net/http"

	"github.com/gin-gonic/gin"

	"vet-health-golang/service"
)

func GetPong(ctx *gin.Context) {
	ctx.JSON(http.StatusOK, gin.H{
		"message": service.FindPong(),
	})
}
