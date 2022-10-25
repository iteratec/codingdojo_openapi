package model

import "time"

type Vaccine struct {
	NAME            string    `json:"name" binding:"required"`                                     //Name of the vaccination
	VACCINATIONDATE time.Time `json:"vaccinationdate" binding:"required" time_format:"2006-01-02"` //Date when the vaccination was done
}
