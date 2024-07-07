import { Avatar, Box, Paper, Typography } from '@mui/material';
import { styled } from '@mui/material/styles';

/* ----------------- Header ------------------ */
export const StyledHeader = styled('header')(() => ({
    display: 'flex', 
    alignItems: 'center', 
    padding: '20px', 
    justifyContent: 'flex-start', 
}));

export const StyledLogo = styled('img')(() => ({
    height: '120px', 
    width: '200px', 
    marginRight: '150px', 
    paddingLeft: '50px', 
    transform: 'scale(1.6)', 
}));

export const StyledNavigation = styled('nav')(() => ({
    display: 'flex', 
    gap: '70px', 
    alignItems: 'center', 
}));

export const NavLink = styled('a')(() => ({
    textDecoration: 'none', 
    color: '#000', 
    fontSize: '16px', 
    fontWeight: 'bold', 
}));

export const SearchBar = styled('div')(() => ({
    position: 'relative', 
    width: '300px', 
    marginLeft: '90px', 
}));

export const ProfileIcon = styled('img')(() => ({
    width: '40px', 
    height: '40px', 
    borderRadius: '50%', 
}));

/* --------------- General ------------------ */

export const Headline = styled(Typography)(() => ({
    marginBottom: '20px', 
    fontWeight: 'bold', 
    color: '#333', 
    fontSize: '3.5rem', 
    textAlign: 'center', 
    padding: '8px 16px', 
    fontFamily: 'Pacifico, sans-serif', 
}));

/* --------------- Carousel ----------------- */

export const CarouselBox = styled(Box)(() => ({
    width: '90%', 
    margin: '-50px auto -50px auto', 
    position: 'relative', 
    height: '600px', 
    borderRadius: '50px', 
}));

export const CarouselImage = styled('img')(() => ({
    width: '100%', 
    height: '100%', 
    transition: 'transform 1s ease-in-out', 
    borderRadius: '50px', 
    overflow: 'hidden', 
    transform: 'scaleY(0.8)', 
}));

/* ------------------ Features ------------------ */

export const FeatureSection = styled(Box)(() => ({
    display: 'flex', 
    flexDirection: 'column', 
    alignItems: 'center', 
    justifyContent: 'center', 
    width: '80%', 
    margin: '0 auto', 
    marginTop: '200px', 
    padding: '32px', 
}));

export const FeatureContainer = styled(Box)(() => ({
    display: 'flex', 
    alignItems: 'center', 
    justifyContent: 'space-between', 
    width: '100%', 
    margin: '32px', 
}));

export const FeatureImageContainer = styled(Box)(() => ({
    flex: '0 0 40%', 
    height: '300px', 
    padding: '16px', 
}));

export const FeatureContentContainer = styled(Box)(() => ({
    flex: '0 0 50%', 
    padding: '16px', 
}));

export const FeatureImage = styled('img')(() => ({
    width: '100%', 
    height: '100%', 
    borderRadius: '8px', 
}));

export const FeatureText = styled(Typography)(() => ({
    fontSize: '16px', 
    lineHeight: 1.6, 
    color: '#333', 
}));

/* ------------- Testimonails ---------------- */

export const TestimonialContainer = styled(Box)(() => ({
    padding: '48px 16px', 
    backgroundColor: '#F9F9F9', 
    width: '80%', 
    margin: '0 auto', 
}));

export const TestimonialCard = styled(Paper)(() => ({
    padding: '32px', 
    margin: '16px', 
    textAlign: 'center', 
    borderRadius: '16px', 
    boxShadow: '0px 2px 4px rgba(0, 0, 0, 0.1)', 
}));

export const TestimonialImage = styled(Avatar)(() => ({
    width: '80px', 
    height: '80px', 
    marginBottom: '16px', 
}));

export const TestimonialQuote = styled(Typography)(() => ({
    fontStyle: 'italic', 
    marginBottom: '16px', 
}));

export const TestimonialName = styled(Typography)(() => ({
    fontWeight: 'bold', 
}));

/* ---------------- Blogs ---------------- */

export const BlogContainer = styled(Box)(() => ({
    padding: '48px 16px', 
    backgroundColor: '#F9F9F9', 
    width: '80%', 
    margin: '0 auto', 
}));

export const BlogCard = styled(Paper)(() => ({
    padding: '32px', 
    margin: '16px', 
    textAlign: 'center', 
    borderRadius: '16px', 
    boxShadow: '0px 2px 4px rgba(0, 0, 0, 0.1)', 
    cursor: 'pointer', 
    transition: 'transform 0.2s', 
    '&:hover': {
        transform: 'scale(1.05)', 
    }, 
    minHeight: '180px', 
}));

export const BlogImage = styled('img')(() => ({
    width: '100%', 
    height: '200px', 
    objectFit: 'cover', 
    borderRadius: '16px 16px 0 0', 
    marginBottom: '16px', 
}));

export const BlogTitle = styled(Typography)(() => ({
    fontWeight: 'bold', 
    marginBottom: '8px', 
}));

export const BlogDescription = styled(Typography)(() => ({
    marginBottom: '16px', 
}));

export const BlogAuthor = styled(Typography)(() => ({
    fontStyle: 'italic', 
}));

/* ---------------- About Us ---------------- */

export const AboutContainer = styled(Box)(() => ({
    padding: '48px 16px', 
    backgroundColor: '#FFF', 
    width: '80%', 
    margin: '0 auto', 
}));

export const SocialMediaContainer = styled(Box)(() => ({
    display: 'flex', 
    justifyContent: 'center', 
    marginBottom: '32px', 
    '& > *': {
        margin: '0 8px', 
    }, 
}));

export const ContactUsContainer = styled(Box)(() => ({
    textAlign: 'center', 
}));

export const ContactInfo = styled(Box)(() => ({
    marginTop: '16px', 
    '& > *': {
        marginBottom: '8px', 
    }, 
}));