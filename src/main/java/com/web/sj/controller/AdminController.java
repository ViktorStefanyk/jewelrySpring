package com.web.sj.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;



@Controller
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class AdminController {
	
	@RequestMapping(value="/add", method=RequestMethod.GET)
	public ModelAndView addNewProduct() {
		ModelAndView model = new ModelAndView();
		model.setViewName("addNewProductpage");
		return model;
	}
	
	@RequestMapping(value="/update/{productId}", method = RequestMethod.GET)
	public ModelAndView updateProduct(@PathVariable(value="productId") Integer productId) {
		ModelAndView model = new ModelAndView();
		model.addObject("productId", productId);
		model.setViewName("updateProductpage");
		return model;
	}
	

	
//	@Autowired public IProductService productService;
//		
//	@RequestMapping(value="/add", method=RequestMethod.GET)
//	public ModelAndView addNewProduct() {
//		ModelAndView model = new ModelAndView();
//		Product product = new Product();
//		List<Gemstone> gemstoneList = productService.getGemstones();
//		List<Category> categoryList = productService.getCategories();
//		List<Material> materialList = productService.getMaterial();
//		List<Type> typeList = productService.getTypes();
//		
//		List<RingSize> ringSizeList = productService.getAllRingSizes();
//		List<ChainsSize> chainsSizeList = productService.getAllChainsSizes();
//		List<PendantSize> pendantSizeList = productService.getAllPendantSizes();
//		
//		List<Gender> genderList = productService.getGenders();
//		
//		product.setProductRingSizes(ringSizeList);
//		product.setProductChainsSizes(chainsSizeList);
//		product.setProductPendantSizes(pendantSizeList);
//		
//		model.addObject("newProduct", product);
//		model.addObject("newGender", genderList);
//		model.addObject("newGemstone", gemstoneList);
//		model.addObject("newCategory", categoryList);
//		model.addObject("newMaterial", materialList);
//		model.addObject("newType", typeList);
//		model.setViewName("addNewProductpage");
//		return model;
//	}
//	
//	@RequestMapping(value="/add", method=RequestMethod.POST)
//	public ModelAndView processAddNewProduct(@ModelAttribute("newProduct") @Valid Product productToBeAdded, BindingResult result, HttpServletRequest request) {
//		ModelAndView model = new ModelAndView();
//		
//		if (result.hasErrors()) {
//			model.setViewName("addProduct");
//			return model;
//		}
//
//		productService.addProduct(productToBeAdded);
//		
//		MultipartFile mainImage = productToBeAdded.getProductMainImage();
////		String rootDirectory = "C:\\Users\\viktors\\workspace\\jewelry\\src\\main\\webapp\\resources\\images\\"; //at work: C:\\Users\\viktors\\workspace\\jewelry\\src\\main\\webapp\\resources\\images\\
//		
//		String root = request.getSession().getServletContext().getRealPath("/");
//		String rootDirectory = root + "resources\\images\\";
//		
//		boolean success = (new File (rootDirectory + productToBeAdded.getProductId())).mkdirs();
//		if (success) {
//		      System.out.println("Folder name: " + productToBeAdded.getProductId() + " created");
//		    }
//		rootDirectory = rootDirectory + productToBeAdded.getProductId();
//		String originName = mainImage.getOriginalFilename();
//		productService.addMainImage(productToBeAdded.getProductId(), originName, "main");
//		if (mainImage != null && !mainImage.isEmpty()) {
//			try {
//				mainImage.transferTo(new File(rootDirectory+"\\"+originName));
//			} catch (Exception e) {
//				throw new RuntimeException("Shoe image saving failed", e);
//			}
//		}
//		
//		List<MultipartFile> additionalImages = productToBeAdded.getProductAdditionalImages();
//		List<String> originAdditionalNames = new ArrayList<String>();
//		
//		if (additionalImages != null && additionalImages.size() > 0) {
//			for (MultipartFile multipartFile : additionalImages) {
//				String originAdditionalName = multipartFile.getOriginalFilename();
//				if (!"".equalsIgnoreCase(originAdditionalName)) {
//					try {
//						multipartFile.transferTo(new File (rootDirectory + "\\" + originAdditionalName));
//						originAdditionalNames.add(originAdditionalName);
//						productService.addMainImage(productToBeAdded.getProductId(), originAdditionalName, "additional");
//					} catch (Exception e) {
//						throw new RuntimeException("Shoe image saving failed", e);
//					}
//					
//				}
//			}
//		}
//		
//		
//		model.setViewName("redirect:/");
//		return model;
//	}

}
