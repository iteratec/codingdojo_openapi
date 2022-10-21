package service

import (
	"vet-health-golang/rest"
)

func FindPong() string {
	return rest.FetchAnimal()
}
