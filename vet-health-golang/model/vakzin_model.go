package model

type Vakzin struct {
	NAME            string `json:"name"`                                               //Name of the vakzination
	VAKZINATIONDATE string `json:"vakzinationdate" format:"date" example:"2021-10-18"` //Date when the vakzination was done
}
