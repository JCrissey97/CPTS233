class HasherFactory {
	public static HasherBase copyHasher(HasherBase to_copy)
	{
		if (to_copy.getClass() == SimpleStringHasher.class)
		{
			return new SimpleStringHasher();
		}
		return new SimpleStringHasher();
	}
}
