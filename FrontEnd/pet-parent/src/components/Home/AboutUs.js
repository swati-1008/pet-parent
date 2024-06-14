import React from "react";
import * as S from './styles';
import { IconButton, Typography } from "@mui/material";
import { Facebook, Instagram, LinkedIn, Twitter } from "@mui/icons-material";

const AboutUs = () => {
    return (
        <S.AboutContainer>
            <S.Headline variant = 'h4' component = 'h2' align = 'center' gutterBottom>
                About Us
            </S.Headline>
            <S.SocialMediaContainer>
                <IconButton href = "https://www.facebook.com" target = "_blank" aria-label = "Facebook">
                    <Facebook fontSize = 'large' />
                </IconButton>
                <IconButton href = "https://www.instagram.com" target = "_blank" aria-label = "Instagram">
                    <Instagram fontSize = 'large' />
                </IconButton>
                <IconButton href = "https://www.twitter.com" target = "_blank" aria-label = "X">
                    <Twitter fontSize = 'large' />
                </IconButton>
                <IconButton href = "https://www.linkedin.com" target = "_blank" aria-label = "LinkedIn">
                    <LinkedIn fontSize = 'large' />
                </IconButton>
            </S.SocialMediaContainer>
            <S.ContactUsContainer>
                <Typography variant = 'h5' component = 'h3' gutterBottom>
                    Contact Us
                </Typography>
                <S.ContactInfo>
                    <Typography variant = 'body1'>Email: info@purrsnpaws.com</Typography>
                    <Typography variant = 'body1'>Phone: 987-654-3210</Typography>
                </S.ContactInfo>
            </S.ContactUsContainer>
        </S.AboutContainer>
    );
};

export default AboutUs;