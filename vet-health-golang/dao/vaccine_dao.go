package dao

import (
	"vet-health-golang/model"
)

var vaccines = make(map[string]*model.Vaccine)
var vaccines_v2 = make(map[string][]model.Vaccine)

func SaveVaccine(name string, vaccine model.Vaccine) {
	vaccines[name] = &vaccine

	//TOTO coment out
	//vaccines_v2[name] = append(vaccines_v2[name], vaccine)
}

func FindAllvaccines(name string) []model.Vaccine {
	return vaccines_v2[name]
}

func FindVaccine(name string) *model.Vaccine {
	return vaccines[name]
}
