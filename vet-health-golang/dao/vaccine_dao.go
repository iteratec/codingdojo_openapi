package dao

import (
	"vet-health-golang/model"
)

var vaccines = make(map[string]*model.Vaccine)

func SaveVaccine(name string, vaccine model.Vaccine) {
	vaccines[name] = &vaccine
}

func FindVaccine(name string) *model.Vaccine {
	return vaccines[name]
}
